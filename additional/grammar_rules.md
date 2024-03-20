# BabyCobol grammar rules
*Latest update: 2024-03-20*

Note: these might be subject to change, meant to be guidelines for development

```
Program ::= IdentificationDivision DataDivision* ProcedureDivision

IdentificationDivision ::= IDENTIFICATION DIVISION. IdClause*
IdClause ::= Identifier. Identifier.

...
```