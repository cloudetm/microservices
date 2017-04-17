puts "** invoking yield **"
def test # test method has yields
  puts "first line in method"
  yield
  puts "second line in method"
  yield
end

test {puts "line in block"}

puts "\n** yield parameters **"
def test2
  yield 1
  puts "You are in test2"
  yield 2, 3
end

test2 {|i| puts "You are in the block and the value is #{i}"}
test2 {|i, j| puts "You are in the block and the values are #{i} and #{j}"}

puts "\n** block_given? **"
def test3
  if block_given?
    yield
  else
    puts "No block passed in"
  end
end

test3
test3 {puts "hello"}
test3 do puts "hello, world" end

# output:
# ** invoking yield **
# first line in method
# line in block
# second line in method
# line in block
#
# ** yield parameters **
# You are in the block and the value is 1
# You are in test2
# You are in the block and the value is 2
# You are in the block and the values are 1 and
# You are in test2
# You are in the block and the values are 2 and 3
#
# ** block_given? **
# No block passed in
# hello
# hello, world
