ByteArraySuffixTree is a Java Implementation of a Suffix Tree for Byte Arrays

Suffix Tree:

A Suffix Tree will take an input 'String'-- in our case a Byte Array, create an entry for each character (byte) and
store the value linked to that String. 

This implementation is not a 'True' Suffix Tree, as it requires that each byte array be of the same size.

http://en.wikipedia.org/wiki/Suffix_tree
http://en.wikipedia.org/wiki/Radix_tree

Suffix Tree vs Dictionary:

In a nutshell: 
Dictionary= O(1) operations, linear memory requirement
SuffixTree= Linear operations, sub-linear memory usage

Consider that we have n byte arrays each of size m:

A dictionary will Hash the Key and Store the Value in constant O(1) time, will use O(n*m) storage. 
Also, a dict will have O(1) updates & retrevials. 

A suffix tree will take O(n) time to store, retreive, and update the value. 
Assuming that our Byte Arrays represent incrementing numbers, we will use far less than O(n*m) storage.
Herein lies the advantage-- smaller memory footprint.

Uses:

Looking at the above complexity, we can see that this is useful where we are dealing with many incrementing numbers
in Byte Array format. 

Note: keep in mind Big vs. Little Endian-- Depending on how your number is represented, where it starts, and how it increments-- it may make more sense to 
store the reverse of the array. 

Example: 

For my purposes, I am using this data structure to store OrderID numbers in ByteArray format and the stored value is a refrence
to the object that related to that OrderID. 

In an environment with (virtually) unrestriced memory, this data structure may not be needed. However, if you are dealing with
100's of millions of messages, this solution will effectively compress the memory footprint and still feature linear operations. 
