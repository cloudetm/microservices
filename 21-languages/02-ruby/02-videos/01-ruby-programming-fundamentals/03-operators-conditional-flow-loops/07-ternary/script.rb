num_1 = "one"

puts "num_1 really does equal one" if num_1 == "one"

num_1 == "one" ? (print "num_1 equals one") : (print "num_1 does not equal one")
puts ""

num_1 == "one" ? num_2 = "two" : num_2 = "one"
puts num_2

# output:
# num_1 really does equal one
# num_1 equals one
# two
