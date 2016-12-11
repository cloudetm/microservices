puts -> (x) {x * x} # #<Proc:0x007feca408ed80@script.rb:1 (lambda)>

puts -> (x) {x * x}.call(8) # 64

square = -> (x) {x * x} # assign a lambda to square
puts square.call(3) # 9

puts "** passing a lambda **"

def do_something(function, argument)
  function.call(argument)
end

puts do_something square, 5 # 25

puts "** passing multiple lambdas **"

def do_something2(functions, argument)
  result = argument
  for f in functions
    result = f.call result
  end
  return result
end

puts do_something2 [-> (x) {x.strip}, -> (x) {x.gsub('...', ' is ')}, -> (x) {x.downcase}], "Lorem Ipsum...simply filler text."

# output:
# #<Proc:0x007fd2338c8fb8@script.rb:1 (lambda)>
# 64
# 9
# ** passing a lambda **
# 25
# ** passing multiple lambdas **
# lorem ipsum is simply filler text.
