# F7

F7 is the spreadsheet formula execution library. It contains a parser, executor, and formulas
necessary to run a spreadsheet. That means you can do things
like `=(SUM(A1:A9) / COUNT(A1:A9)) * MAX(Sheet1!A1:A)`
or basically anything else you'd want to write in a cell in a common spreadsheet.

I built it back in 2019 for a product that never ended up launching, and thought I'd open source it.
I'm not sure how usable it is in your application. It's not exactly plug-and-play, so to speak, but
there might be something that someone can learn from it.

I used ANTLR G4 to write grammars that produce JS/TS parser/lexers to get the AST, and then uses an
engine and some formulas to compute the cell values.

It mostly supports:

* Basic math, including order of operations.
* Relative and absolute A1-notation references.
* Sheets.
* Sheet references.
* Formulas for engineering, info, logic, math, parser, statistical, and text.
* Most formulas check types, so they behave similarly to common spreadsheets.
* Named ranges.
* Excel-like data types: Boolean, Number, Error, String, Date, Empty.
* Some Google Sheets formulas, some Excel formulas.
* Errors that work like Google Sheets including #DIV, and, for example, circular-dependency errors.
* Array projection using `= {1, 2, 3}` notation.
* Grid projection using `= {1, 2; 3; 4; 5, 6}` notation.

Here are the things it does not support:

* XML import or export. Since this seemed like the easiest part, I didn't worry about it, assuming
  I'd be able to find a good XML parser to wrap the models.
* Good memory management. It stores basically everything using in-memory hash maps.
* Performance. For example, it mostly uses iteration for hashmap lookups when doing range-based
  queries.
* Google Sheets LTR-style, left-hand association of the `^` power operator. (Google Sheets, and
  Excel are different, so `=2^3^4` in Excel is 4096, but something like 2.417851639E24 in Google
  Sheets.)
* Row-column notation.

One thing to note is that exponential operators are left-hand associative. Google Sheets, and Excel
are different, so `=2^3^4` in Excel is 4096, but something like 2.417851639E24 in Google Sheets. You
can find out more about these strange discrepancies if you grep the code for "HACK" or "TODO".

If I had to do this again, I would do it very differently. But I certainly had fun the first time.

## License

Copyright 2022 Ben Vogt.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
