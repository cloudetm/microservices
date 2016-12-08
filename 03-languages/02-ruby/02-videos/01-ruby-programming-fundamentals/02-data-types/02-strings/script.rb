my_string = "just a string"
puts my_string

puts "I'm just a string"

puts "I\"m just a string" # backslash escapes escape characters

start_string = "I'm "
puts "1: " + start_string + my_string
puts "2: " + start_string << my_string # << is better perf than +
puts "3: " + "#{start_string}--#{my_string}" # double quote evaluate expression
puts "4: " + start_string.concat(my_string) # concat and assign it to the first string var
puts "5: " + start_string

puts start_string [0] # `I` access character sequence by using index which it is NOT array
puts "0123456"[3] # `3`
puts "0123456"[2,4] # `2345` get four characters start from index 2
puts "0123456"[2..4] # `234` get characters from index 2 to 4
