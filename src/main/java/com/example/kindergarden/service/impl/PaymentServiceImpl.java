package com.example.kindergarden.service.impl;

import com.example.kindergarden.exception.ConflictException;
import com.example.kindergarden.exception.NotFoundException;
import com.example.kindergarden.mapper.PaymentMapper;
import com.example.kindergarden.models.GroupChildren;
import com.example.kindergarden.models.Payment;
import com.example.kindergarden.models.dto.PaymentCreateDto;
import com.example.kindergarden.models.dto.PaymentDto;
import com.example.kindergarden.models.dto.PreviousMonthDebtDto;
import com.example.kindergarden.repositories.GroupChildrenRepository;
import com.example.kindergarden.repositories.PaymentRepository;
import com.example.kindergarden.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepo;
    private final GroupChildrenRepository groupChildrenRepo;
    private final PaymentMapper mapper;

    @Override
    public PaymentDto create(PaymentCreateDto dto) {
        GroupChildren gc = groupChildrenRepo.findByChild_IdAndEndDateIsNull(dto.getGroupChildrenId())
                .orElseThrow(() -> new NotFoundException("Ребенок не найден в группе или уже отчислен"));

        if (dto.getAmount() == null || dto.getAmount() <= 0) {
            throw new ConflictException("Сумма платежа должна быть положительной");
        }
        if (dto.getPaymentDate() == null) {
            throw new ConflictException("Дата платежа обязательна");
        }
        if (dto.getPaymentDate().isAfter(LocalDate.now())) {
            throw new ConflictException("Дата платежа не может быть в будущем");
        }

        Payment payment = mapper.toEntity(dto);
        payment.setGroupChildren(gc);

        return mapper.toDto(paymentRepo.save(payment));
    }

    @Override
    public PreviousMonthDebtDto getPreviousMonthDebt(Long childId) {
        GroupChildren gc = groupChildrenRepo.findByChild_IdAndEndDateIsNull(childId)
                .orElseThrow(() -> new NotFoundException("Ребенок не найден в группе или уже отчислен"));

        YearMonth prevMonth = YearMonth.now().minusMonths(1);
        LocalDate start = prevMonth.atDay(1);
        LocalDate end = prevMonth.atEndOfMonth();

        if (gc.getStartDate().isAfter(end)) {
            throw new ConflictException("Ребенок не посещал садик в прошлом месяце");
        }

        List<Payment> payments = paymentRepo.findAllByGroupChildren_IdAndPaymentDateBetween(
                gc.getId(),
                start,
                end
        );

        int totalPaid = payments.stream().mapToInt(Payment::getAmount).sum();
        int price = gc.getPrice() != null ? gc.getPrice() : gc.getGroup().getPrice();

        PreviousMonthDebtDto result = new PreviousMonthDebtDto();
        result.setId(childId);
        result.setAmountDue(Math.max(price - totalPaid, 0));

        return result;
    }
}
