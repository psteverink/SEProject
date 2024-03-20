# BabyCobol features implementation status

Just a recap and some notes for the features implementation.

*Latest update: 2024-03-20*

## Position-based parsing ![](https://geps.dev/progress/90)

### Progress
<details>
<summary>2024-03-20</summary>
Involved classes: PreProcessor
</details>

### Not yet implemented/considered

- [ ] Line continuations behavior


## Identification division parsing ![](https://geps.dev/progress/50)

### Progress

<details>
<summary>2024-03-20</summary>
Done with the lexing part, actual parsing still to do.

Involved classes: StatefulLexer, Parser, KeywordTrie

#### Notes for future updates
Some examples of potential errors to report from the parser.
- Incomplete pairs

```
IDENTIFICATION DIVISION.
	PROGRAM-ID. MY-PROGRAM.
	AUTHOR.
```

Missing value from the second clause

- Missing dots

```
IDENTIFICATION DIVISION    # Can be still parsed correctly but missing dot should be reported
	PROGRAM-ID. MY-PROGRAM.  
	AUTHOR Myself            # Will be tokenized as a single identifier and missing dot + missing value -> to the parser
```

- Wrong division start (not column 8) and/or wrong clause start (not column 12) → parsing can continue but issue should be reported
- Sentences in the division (aside from COPY)

```
IDENTIFICATION DIVISION.
	DISPLAY "Hello world".
```

Should be tokenized normally (KEYWORD + LITERAL) - parser should complain later

- Unclear if things like this should be valid or not:

```
IDENTIFICATION DIVISION.
	01-PROGRAM_ID. $myAwesome (Pro=gram.
	0002. 11111.
```

According to the documentation any symbol, digit or letter is allowed except for dot. For now the lexer assumes it's valid.

</details>

### Not yet implemented/considered

- [ ] Representation of division in the parse tree
- [ ] Presence of COPY directive inside the identification division
- [ ] Signaling of errors in parsing phase

## Procedure division parsing ![](https://geps.dev/progress/30)

### Progress

<details>
<summary>2024-03-20</summary>
Implemented lexing for string and number literals, identifiers/keywords in progress.
All methods tested for correctness (StatefulLexerTest)
</details>

### Not yet implemented/considered
- [ ] identifiers/keywords (in progress)
- [ ] arithmetic operators
- [ ] expression grouping
- [ ] constructs (loops, if-else, ...)

## White space insignificance ![](https://geps.dev/progress/15)

### Progress

<details>
<summary>2024-03-20</summary>
Partially implemented white space insignificance for keywords and number literals. In progress for identifiers.
</details>

#### For keywords
White space insignificance for keywords is implemented via the KeywordTrie structure: it can be traversed character by character and if we have a keyword with 2 or more words, white spaces in between are simply skipped (e.g. "IDENTIFICATION DIVISION" -> found in the trie with or without space). Tested in KeywordTrieTest.

#### For clauses in IDENTIFICATION DIVISION
Since names and values in identification division are allowed to have spaces, tokenizing happens either at the end of the line or at the occurrence of a dot.

#### For number literals
Different scenarios.

A) Integers separated by space
```
ADD 1 2 3 TO X.
```
Produces 3 separate tokens according to whitespaces (1, 2, 3). Defers to the parser the interpretation according to the context: here for example, ADD can accept more than one literal, so 3 different numbers are accepted as valid.

B) Decimals with spaces in between
```
ADD 1. 23 TO X.
ADD 1, 23 TO X.
ADD 1 .23 TO X.
ADD 1 ,23 TO X.
```
They all get tokenized as 3 different pieces, 1, comma or dot and 23. Deferred to the parser the interpretation according to the context.

### Not yet implemented/considered

- [ ] Proper white space insignificance for identifiers (in progress)

## Keywords disambiguation ![](https://geps.dev/progress/10)

### Progress
<details>
<summary>2024-03-20</summary>
Partially implemented in lexing phase: if all uppercase identifier is detected in the KeywordTrie it is classified as keyword (precedence).
Actual disambiguation must be implemented in the parser with context.
</details>

### Not yet implemented/considered

- [ ] Disambiguation between identifiers in uppercase and keywords