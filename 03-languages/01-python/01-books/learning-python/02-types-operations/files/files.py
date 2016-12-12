myfile = open('temp.txt', 'w')  # Open for text output: create/empty
myfile.write('hello text file\n')  # Write a line of text: string
myfile.write('goodbye text file\n')
myfile.close()  # Flush output buffers to disk

myfile = open('temp.txt')  # Open for text input: 'r' is default
myfile.readline()  # Read the lines back
myfile.readline()
myfile.readline()  # Empty string:end-of-file

print(open('temp.txt').read())

for line in open('temp.txt'):
    print(line)
