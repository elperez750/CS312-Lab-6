# Lab 6: Hamming Code Error Detection and Correction

## Project Overview
This project involves processing binary data transmissions to identify and correct bit-level errors using (21, 16) Hamming Codes. The system compares a "Golden" transmitted file against multiple received versions to demonstrate the resilience of Single Error Correction (SEC) algorithms.

## Technical Problem
Digital transmissions are susceptible to noise that can flip individual bits. This project implements a syndrome-based correction system that:
1. Reads raw bitstreams from `.bin` files.
2. Identifies 21-bit Hamming blocks.
3. Calculates parity check sums to generate a **Syndrome**.
4. Flips the corrupted bit (if any) to restore data integrity.



## Implementation Details

### 1. File Processing
The system reads five distinct binary files:
* `transmitfile.bin`: The original, error-free data.
* `received1.bin` through `received4.bin`: Versions of the transmission with various levels of bit-rot.

Data is read into `byte` arrays. Since the files are stored as ASCII bit-characters ('0' and '1'), a conversion factor of `-48` is applied to transform characters into mathematical integers for XOR operations.

### 2. The Hamming Logic
The implementation follows the (21, 16) standard, utilizing:
* **16 Data Bits:** The actual message content.
* **5 Parity Bits:** Located at indices that are powers of 2 ($2^0, 2^1, 2^2, 2^3, 2^4$).



### 3. Syndrome Calculation
A 5-bit syndrome is calculated by XORing specific parity groups. 
* A syndrome of `0` indicates an error-free block.
* A non-zero syndrome (e.g., `49`) indicates a bit flip at that specific decimal index.



## Key Features
* **Bit-Level Manipulation:** Utilizes `StringBuilder` and `char` casting to visualize raw bitstreams for debugging.
* **Stability:** Ensures that the relative order of data remains intact while only modifying the specific corrupted bit.
* **Verification:** Cross-references corrected "Received" strings against the "Transmitted" string to prove 100% recovery.

## How to Run
1. Ensure `transmitfile.bin` and the `received.bin` files are in the project root directory.
2. Compile the project:
   ```bash
   javac HammingLab.java
