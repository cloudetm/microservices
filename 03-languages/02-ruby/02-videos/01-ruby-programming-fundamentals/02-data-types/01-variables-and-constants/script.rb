var_1 = "variable1"
var_2 = "variable2"

combination = var_1 + " " + var_2 + "\n"

print combination

num_1 = 5
num_2 = 10

print 5 + num_1, "\n"

print num_1 + num_2
print "\n"

CONST = 20
NEWLINE = "\n"

print num_1 + CONST, NEWLINE

puts "this is a constant value: " + CONST.to_s

# note:
# print does not end with newline
# puts ends with newline
#
# output
# $ ruby script.rb
# variable1 variable2
# 10
# 15
# 25
# this is a constant value: 20
