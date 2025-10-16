<?xml version="1.0" encoding="utf-8" ?>
<!-- SQL XML created by WWW SQL Designer, https://github.com/ondras/wwwsqldesigner/ -->
<!-- Active URL: https://sql.toad.cz/ -->
<sql>
    <datatypes db="mysql">
        <group label="Numeric" color="rgb(238,238,170)">
            <type label="Integer" length="0" sql="INTEGER" quote=""/>
            <type label="TINYINT" length="0" sql="TINYINT" quote=""/>
            <type label="SMALLINT" length="0" sql="SMALLINT" quote=""/>
            <type label="MEDIUMINT" length="0" sql="MEDIUMINT" quote=""/>
            <type label="INT" length="0" sql="INT" quote=""/>
            <type label="BIGINT" length="0" sql="BIGINT" quote=""/>
            <type label="Decimal" length="1" sql="DECIMAL" re="DEC" quote=""/>
            <type label="Single precision" length="0" sql="FLOAT" quote=""/>
            <type label="Double precision" length="0" sql="DOUBLE" re="DOUBLE" quote=""/>
        </group>

        <group label="Character" color="rgb(255,200,200)">
            <type label="Char" length="1" sql="CHAR" quote="'"/>
            <type label="Varchar" length="1" sql="VARCHAR" quote="'"/>
            <type label="Text" length="0" sql="MEDIUMTEXT" re="TEXT" quote="'"/>
            <type label="Binary" length="1" sql="BINARY" quote="'"/>
            <type label="Varbinary" length="1" sql="VARBINARY" quote="'"/>
            <type label="BLOB" length="0" sql="BLOB" re="BLOB" quote="'"/>
        </group>

        <group label="Date &amp; Time" color="rgb(200,255,200)">
            <type label="Date" length="0" sql="DATE" quote="'"/>
            <type label="Time" length="0" sql="TIME" quote="'"/>
            <type label="Datetime" length="0" sql="DATETIME" quote="'"/>
            <type label="Year" length="0" sql="YEAR" quote=""/>
            <type label="Timestamp" length="0" sql="TIMESTAMP" quote="'"/>
        </group>

        <group label="Miscellaneous" color="rgb(200,200,255)">
            <type label="ENUM" length="1" sql="ENUM" quote=""/>
            <type label="SET" length="1" sql="SET" quote=""/>
            <type label="Bit" length="0" sql="bit" quote=""/>
        </group>
    </datatypes><table x="448" y="196" name="group_categories">
    <row name="id" null="1" autoincrement="1">
        <datatype>INTEGER</datatype>
        <default>NULL</default></row>
    <row name="name" null="1" autoincrement="0">
        <datatype>VARCHAR(255)</datatype>
        <default>NULL</default></row>
    <row name="active" null="1" autoincrement="0">
        <datatype>INTEGER</datatype>
        <default>NULL</default><comment>Is active or not</comment>
    </row>
    <row name="price" null="1" autoincrement="0">
        <datatype>INTEGER</datatype>
        <default>NULL</default><comment>Category price</comment>
    </row>
    <key type="PRIMARY" name="null">
        <part>id</part>
    </key>
</table>
    <table x="757" y="180" name="groups">
        <row name="id" null="1" autoincrement="1">
            <datatype>INTEGER</datatype>
            <default>NULL</default></row>
        <row name="name" null="1" autoincrement="0">
            <datatype>VARCHAR(255)</datatype>
            <default>NULL</default></row>
        <row name="max_children_count" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default></row>
        <row name="price" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default><comment>Monthly price (default from category, but can be customized)</comment>
        </row>
        <row name="nanny_id" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default><relation table="teachers" row="id" />
        </row>
        <row name="group_category_id" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default><relation table="group_categories" row="id" />
        </row>
        <row name="teacher_id" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default><relation table="teachers" row="id" />
        </row>
        <key type="PRIMARY" name="null">
            <part>id</part>
        </key>
    </table>
    <table x="1119" y="175" name="teachers">
        <row name="id" null="1" autoincrement="1">
            <datatype>INTEGER</datatype>
            <default>NULL</default></row>
        <row name="first_name" null="1" autoincrement="0">
            <datatype>VARCHAR(255)</datatype>
            <default>NULL</default></row>
        <row name="last_name" null="1" autoincrement="0">
            <datatype>VARCHAR(255)</datatype>
            <default>NULL</default></row>
        <row name="patronymic" null="1" autoincrement="0">
            <datatype>VARCHAR(255)</datatype>
            <default>NULL</default></row>
        <row name="teacher_degree" null="1" autoincrement="0">
            <datatype>VARCHAR(255)</datatype>
            <default>NULL</default><comment>Role level: teacher, nanny, assistant</comment>
        </row>
        <row name="active" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default><comment>Currently working or not</comment>
        </row>
        <key type="PRIMARY" name="null">
            <part>id</part>
        </key>
    </table>
    <table x="269" y="520" name="children">
        <row name="id" null="1" autoincrement="1">
            <datatype>INTEGER</datatype>
            <default>NULL</default></row>
        <row name="first_name" null="1" autoincrement="0">
            <datatype>VARCHAR(255)</datatype>
            <default>NULL</default></row>
        <row name="last_name" null="1" autoincrement="0">
            <datatype>VARCHAR(255)</datatype>
            <default>NULL</default></row>
        <row name="patronymic" null="1" autoincrement="0">
            <datatype>VARCHAR(255)</datatype>
            <default>NULL</default></row>
        <row name="date_of_birth" null="1" autoincrement="0">
            <datatype>DATE</datatype>
            <default>NULL</default></row>
        <key type="PRIMARY" name="null">
            <part>id</part>
        </key>
    </table>
    <table x="554" y="435" name="group_children">
        <row name="id" null="1" autoincrement="1">
            <datatype>INTEGER</datatype>
            <default>NULL</default></row>
        <row name="child_id" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default><relation table="children" row="id" />
        </row>
        <row name="group_id" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default><relation table="groups" row="id" />
        </row>
        <row name="start_date" null="1" autoincrement="0">
            <datatype>DATE</datatype>
            <default>NULL</default></row>
        <row name="end_date" null="1" autoincrement="0">
            <datatype>DATE</datatype>
            <default>NULL</default></row>
        <row name="price" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default><comment>Individual price for the child</comment>
        </row>
        <key type="PRIMARY" name="null">
            <part>id</part>
        </key>
    </table>
    <table x="909" y="531" name="payments">
        <row name="id" null="1" autoincrement="1">
            <datatype>INTEGER</datatype>
            <default>NULL</default></row>
        <row name="group_child_id" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default><relation table="group_children" row="id" />
        </row>
        <row name="amount" null="1" autoincrement="0">
            <datatype>INTEGER</datatype>
            <default>NULL</default></row>
        <row name="payment_date" null="1" autoincrement="0">
            <datatype>DATE</datatype>
            <default>NULL</default></row>
        <key type="PRIMARY" name="null">
            <part>id</part>
        </key>
    </table>
</sql>