表达式引擎


----
一. 组成

1. 表达式

    (0) or (1) and (2) and (3)

2. 表达式单元

    ```
    Field

        type 布尔/字符串/列表/数字
        name  字段名称
        operator 操作符
        value  值
    ```
3. 表达式类型的操作符

    1.1 公共操作符

    ```
    为空
        isNull
    不为空
        isNotNull
    ```

    1.2 布尔
    ```

    是
        isTrue
    否
        isFalse
    ```

    1.3 字符串
    ```

    为空
        isEmpty
    不为空
        isNotEmpty
    包含
        contains
    不包含
        notContains
    ```

    1.4 列表
    ```


    在...中
        in
    不在...中
        notIn
    相交
        intersect
    不相交
        disjoint
    包含
        contains
    不包含
        notContains
    为空
        isEmpty
    不为空
        isNotEmpty
    ```

    1.5 数字

    ```
    gt
    gte
    lt
    lte
    ```

4. 设计

    ```
    ExpressionEngine
        eval
    参数
        Map
    变量值
    binds
        RuleBean
    expression
    expressions
        ExpressionUnit
    field
        type
        name
    operator
    value
    返回值
        ExpressionEvalResult
    code
    msg
    @ExpressionFieldType
        value
    FieldType
        子类
    BooleanFieldTypeImpl
    StringFileTypeImpl
    ListFieldTypeImpl
    NumberFieldTypeImpl
        eval
    ExpressionUnit
    binds