puts "** variable ousdie block does not affect variable inside block **"

a = 10
5.times do |a|
  puts "a inside the block: #{a}"
end

puts "a outside the block: #{a}"

puts "** changing variable outside block **"

b = 10
5.times do |y|
  b = y
  puts "b inside the block: #{b}"
end

puts "b outside of the block: #{b}"

# output:
# ** variable ousdie block does not affect variable inside block **
# a inside the block: 0
# a inside the block: 1
# a inside the block: 2
# a inside the block: 3
# a inside the block: 4
# a outside the block: 10
# ** changing variable outside block **
# b inside the block: 0
# b inside the block: 1
# b inside the block: 2
# b inside the block: 3
# b inside the block: 4
# b outside of the block: 4
