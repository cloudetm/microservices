my_array = ["this", "is", "a", "sentence"]
puts my_array[0] # this
puts my_array.first # this
puts my_array.last # sentence
puts my_array[3] = "test" # test
print my_array, "\n" # ["this", "is", "a", "test"]
my_array.push(".")
print my_array, "\n" # ["this", "is", "a", "test", "."]
puts my_array.pop # .
print my_array, "\n" # ["this", "is", "a", "test"]
puts my_array.delete_at(2) # a
print my_array, "\n" # ["this", "is", "test"]
puts my_array.count # 3
puts my_array.delete("this") # this
print my_array, "\n" # ["is", "test"]
my_array_2 = [1, "is", 2, 3]
print my_array_2, "\n" # [1, "is", 2, 3]
print my_array_3 = my_array + my_array_2, "\n" # ["is", "test", 1, "is", 2, 3]
print my_array_3 = my_array - my_array_2, "\n" # [test] - remove "is" which exists in my_array_2
print my_array_3 = my_array_2 - my_array, "\n" # [1, 2, 3] - remove "is" which exists in my_array

my_array = ["this", "is", "a", "sentence"]

puts my_array.join # thisisasentence
puts my_array.join(' ') # this is a sentence

number_array = [1,2,3,4]
print number_array.first(3), "\n" # [1, 2, 3]
print number_array.last(3), "\n" # [2, 3, 4]
