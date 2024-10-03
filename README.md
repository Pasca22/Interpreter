
# Toy-Language-Interpreter

Welcome to **Toy-Language-Interpreter**, a C++ project designed to interpret a custom toy language with support for common programming constructs and multi-threading. This interpreter allows you to define variables, perform logical, relational, and arithmetic operations, use control flow statements, manage memory with garbage collection, and execute code on threads.

## Features

- **Variable Types**: 
  - Supports `bool`, `int`, and `string` data types.
  
- **Operations**: 
  - Perform **arithmetic operations** such as addition, subtraction, multiplication, and division on numeric types.
  - Perform **logical operations** such as AND, OR, and NOT on boolean types.
  - Execute **relational operations** like greater than, less than, and equality on compatible types.

- **Control Flow**: 
  - Supports `if` statements for conditional branching.
  - `while` loops and `do-while` loops for iterative execution.
  - `for` loops to iterate through values.
  
- **Garbage Collection**: 
  - Automatically manages memory through garbage collection, cleaning up unused variables to prevent memory leaks.

- **Pointers**: 
  - Supports pointer manipulation for advanced memory control.
  
- **Multithreading**: 
  - Run code on multiple threads to leverage concurrent execution and improve performance in certain tasks.

## How to Use

1. **Defining Variables**  
   You can define variables of type `bool`, `int`, or `string` and assign values to them. The interpreter will manage variable storage and garbage collection.

2. **Performing Operations**  
   The interpreter supports various operations: arithmetic for `int`, logical for `bool`, and relational comparisons for all supported types.

3. **Control Flow Statements**  
   Use `if`, `while`, `for`, and `do-while` loops to control the flow of the program, just like in a typical programming language.

4. **Memory Management**  
   Memory is automatically managed with garbage collection, but you can also use pointers for direct memory manipulation when necessary.

5. **Multithreading**  
   Execute blocks of code in separate threads to perform concurrent operations, which is ideal for tasks that can be parallelized.
