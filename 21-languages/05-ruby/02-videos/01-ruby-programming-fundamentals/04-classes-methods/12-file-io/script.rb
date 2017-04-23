puts "** Setup **"

File.open('text.txt', 'w') do |f| # create file
  f.puts 'line 1'
  f.puts 'line 2'
  f.puts 'line 3'
end

puts "** Read file **"

def readFile
  File.open('text.txt', 'r') do |f|
    while line = f.gets
      puts line
    end
  end
end

readFile

puts "** Write file - overwrite file **"
File.open('text.txt', 'w') do |f|
  f.puts 'newest line'
end

readFile

puts "** Append to file **"
open('text.txt', 'a') do |f|
  f.puts 'This line is being appended'
end

readFile

puts "** Append with block and << syntax **"
open('text.txt', 'a') {|f|
  f << "appended line 1\n"
  f << "appended line 2\n"
}

readFile

puts "** Assign file lines to array **"
lines = [] # create an array
file = File.open('text.txt', 'r')
while line = file.gets
  lines << line
end
file.close # close file because file handle is outside of block

puts "-- Array of lines --"
lines.each {|l| puts l}

puts "** Cleanup **"
File.delete('text.txt') # delete file at the end

# output:
# ** Setup **
# ** Read file **
# line 1
# line 2
# line 3
# ** Write file - overwrite file **
# newest line
# ** Append to file **
# newest line
# This line is being appended
# ** Append with block and << syntax **
# newest line
# This line is being appended
# appended line 1
# appended line 2
# ** Assign file lines to array **
# -- Array of lines --
# newest line
# This line is being appended
# appended line 1
# appended line 2
# ** Cleanup **
