Problem:

Write a java function which takes a two dimensional array
as input. The array can have any number of rows or columns.

Each cell in an array has either a 0 or a 1. We have to walk from
cell (0,0) to cell(n,m) where and n, m are count of rows and columns
in the array. 
While walking if the cell contains 0 we have to stop. If the cell has
a 1 we can go right or down.
The function has to return how many possible paths are there for
us to go from (0,0) to (n,m).