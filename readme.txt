May 9th, 2023

General Algorithm:
I think I did pretty well on the general algorithm for this assignment. Each time an order can be fulfilled, any other orders that can be
fulfilled are also taken care of. To do this, I used two while loops: one to get the records from the file, and another inside it to process
as many orders as possible as long as the chicks were in stock. I also used my push and pop methods to easily take off Orders and Shipments
from the Queue or Stack, and made sure the correct shipment or order was being accessed. After counting up all the chicks and the completed
orders, it was consistent with the example output provided.

Queue and Stack Classes:
I think I did a good job using ArrayLists to implement Queue and Stack classes. Each class has appropriate methods, and works the way they
are intended to. 
While this wasn't part of the assignment instructions, I also created Shipment and Order classes to better manage data from
the records. I believe the implementation of these classes were also clean and good, and grouping the data for each part of the order or shipment
has been very helpful. 

Total calculating:
This was easily the roughest part of this assignment. I think it would have been fine if the price per chick never changed, but figuring
out the ways to calculate days into it was very challenging. At first, I had no idea how to get the days to work, but after a while I figured out
you can get the days you needed by simply storing the day that the shipment was stored in, and subtracting it by the number of days passed. As soon as
I figured that out, I was able to get the correct totals with little issue. 

Table output:
Outputting the table at the end that holds all the values was fine. I used a conditional to control how much space each line has, which
made it look more nicer and aligned. I was able to space out the lines using print format, which is something I never knew you could do
with that method. 

Average price of chicks / average days in warehouse:
This is another thing I once had trouble with, but after I figured out how to get the total days easily, it wasn't much of an issue. I used ArrayLists to store
each time or price of the chick, and then took the average of that at the end of the program. I did this instead of using a single double or integer variable
because I could get the total number using the ArrayLists.size() method, which made averaging it easier. 

Overall, I think everything in the program works fine. I hope the comments I left make it easy to follow. 

SPECIAL INSTRUCTIONS:
The input file should not have any extra spaces after each record. (For example, a line [O, name, 300, 3] should not 
be [O, name, 300, 3     ] ). This causes the program to error, due to how my delimiter is set up.

Besides that, any files that follow the same record format should work. 