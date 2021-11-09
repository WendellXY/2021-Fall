# 3740 Class Activity - Group A

## Main

Group Work: An Introduction of MySQL Trigger

### MySQL Trigger Syntax Part

#### Syntax Overview

To create a trigger or drop a trigger, use the `CREATE TRIGGER` and `DROP TRIGGER` statement.

#### Create a MySQL Trigger

```mysql
CREARE
	[DEFINER = user]
	TRIGGER trigger_name
	trigger_time trigger_event
	ON tbl_name FOR EACH ROW
	trigger_body
	
trigger_time: { BEFORE | AFTER }
trigger_event: { INSERT | UPDATE | DELETE }
```

*`trigger_time`* is the trigger action time. It can be `BEFORE` or `AFTER` to indicate that the trigger activates before or after each row to be modified.

```mysql
Before Trigger activates
Something Changes
After Trigger activates
```

*`trigger_event`* indicates the kind of operation that activates the trigger, in other words, when those operations happens, the trigger activates.

*`trigger_body`* is the statement to execute when the trigger activates.

Within the trigger body, you can refer to columns in the subject table (the table associated with the trigger) by using the aliases `OLD` and `NEW`.`OLD`.`col_name` refers to a column of an existing row before it is updated or deleted. `NEW`.`col_name` refers to the column of a new row to be inserted or an existing row after it is updated.

###### Simple Example of Trigger Creation

```mysql
CREATE TABLE account (acct_num INT, amount DECIMAL(10,2));

CREATE 
	TRIGGER ins_sum 
	BEFORE INSERT 
	ON account FOR EACH ROW 
	SET @sum = @sum + NEW.amount;
```

So, here we created a trigger named `ins_sum`, which triggerred **before** the **insert** action happens. For example, here is the example that how our trigger activated.

```mysql
mysql> SET @sum = 0;
mysql> INSERT INTO account VALUES(1, 1.10),(2, 2.20),(3, 3.30);
mysql> SELECT @sum AS 'Total amount inserted';
+-----------------------+
| Total amount inserted |
+-----------------------+
|                6.60   |
+-----------------------+
```

In this case, the value of `@sum` after the `INSERT` statement has executed is `1.10 + 2.20 + 3.30`, or `6.60`.

##### Delete a MySQL Trigger

To destroy the trigger, use a `DROP TRIGGER` statement. You must specify the schema name if the trigger is not in the default schema:

```mysql
DROP TRIGGER [IF EXISTS] [schema_name.]trigger_name
```

###### Simple Example

```mysql
DROP TRIGGER test.ins_sum;
```



