#for string it should be wrapped with double quotes "abc"

Feature: Calculator Functionality

Background:
Given a and b

@add
Scenario: Addition of two numbers
When i add a and b
Then result should be sum of two numbers

@sub
Scenario: Subtraction of two numbers
Given a as 10 and b as 2
When i subtract a and b
Then result should be 8

@mul
Scenario: Multiplication of two numbers
When i Multiple a and b
Then result should be Multiplication of two numbers

@add
Scenario Outline: Addition of two numbers
Given a as <A> and b as <B>
When i add a and b
Then result should be <res>

Examples:
|A|B|res|
|10|10|20|
|20|20|40|
|50|50|100|

@table
Scenario: Data table functionality
Given a and b and c and d
|25|15|
|30|30|
When i add a and b
Then result should be sum of two numbers

