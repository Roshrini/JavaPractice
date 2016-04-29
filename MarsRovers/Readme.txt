Steps took to solve this problem:
1) First read it and understood it by drawing grid and doing manual movement according to the given series 
of instructions and checking output to the given output.
2) Once I was sure that I understood the problem correctly, I started planning about how to design the solution.
-> First way was to make use of data structures like Arraylist or HashMap to keep track of the positions 
throughout the movement 
-> Second way was to use object oriented approach and make rover as new class and use constructors and methods to 
keep track of changing positions throughout the series of instructions.

I decided to take second way.

3) Then, I started jotting down some approaches to solve the exact problem.

-> First basic way was to move the rover by using if else conditions and original position. But this way will take
a lots of lines of code and will not be too modular

-> Second way was to use indexes of NESW directions and do calculation on the indexes to keep track of the position.
I also used move_offset array to move rover. e.g to go up in grid, add (0,1) to current coordinate 
I found it easy and most proper way to do it. 

Some things I took care of:
-> Getting file and parsing it.
-> Checking content of file. If it is null or not.
-> Checking boundary conditions so that rover movement should not go outside grid and we should not get 
ArrayIndexOutOfBounds Exception.
-> Closing filestream to avoid resource leak.

I used Eclipse IDE to do my coding in Java

