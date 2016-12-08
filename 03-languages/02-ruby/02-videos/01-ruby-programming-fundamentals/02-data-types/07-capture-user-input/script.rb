puts "what is your name?"
name = gets.chomp # read input string, `chomp` cuts off the new line
puts "Hi #{name}, I'm Ruby"
puts "Give me a number"
number = gets.chomp.to_i + 5
puts "I just added 5 to your number and now it is #{number}."

# output
# what is your name?
# Tom
# Hi Tom, I'm Ruby
# Give me a number
# 3
# I just added 5 to your number and now it is 8.
