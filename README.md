### File Processing System  

A program that uses Java concurrency to receive text files from the user and process them to write reports of the number of lines, words, and character count in the document.  
***
This project is built as a beginner Java project to practice executor service, virtual threads, locks, and receiving input from the user beyond terminal entries.
***
Example of a report:  
File: test.txt | Lines: 8 | Words: 127 | Characters: 681  
***
Prerequisites:  
JDK 21 or higher  
***
How to run this program:  
1. Clone the repository:  
git clone https://github.com/Sarthi267/FileProcessor.git  
2. Move into the project directory:  
cd FileProcessor 
3. Create a folder titled "input", ("output", and "processed" folders will be created automatically after running)
4. Drop any .txt files you want processed into the input/ folder  
5. Compile javac -cp . src/com/fileprocessor/*.java -d out  
6. Run:  
java -cp out com.fileprocessor.Main
7. Find your results:  
- Report generated in output/ with a timestamp
- Processed files moved to processed/[timestamp]/
***
### Notes  
- Each run generates a new timestamped report  
- Input folder clears automatically after each run  
- Supports multiple files processed concurrently


