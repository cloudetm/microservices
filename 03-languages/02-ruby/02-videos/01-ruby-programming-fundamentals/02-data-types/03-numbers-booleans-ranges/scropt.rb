puts 1 + 2 # 3
puts 1 * 2 # 2
puts 1 - 3 # -2
puts (1-3).abs # 2
puts 5**2 # 5 power 25
puts 1/3 # 0
puts 1.0/3 # 0.3333
puts 5%3 # 2
puts 1.integer? # true

puts "\n**boolean**"

puts true && true # true
puts true && false # false
puts false && true # false
puts true || true # true
puts true || false # true
print "1==1: ", 1==1, "\n" # true
puts "1" == "1" # true
puts 1 != 1 # false

puts "\n**ranges**"

puts (1..4).to_a # `1 2 3 4` - includes index start and end indexes
puts (-4..0).to_a # `-4 -3 -2 -1 0`
puts (1...4).to_a # `1 2 3` - does NOT include end index
