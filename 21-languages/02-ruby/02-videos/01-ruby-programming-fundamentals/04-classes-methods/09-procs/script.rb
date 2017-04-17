# proc = pointer to a function

proc = Proc.new {puts "inside the block"}
puts proc.class # Proc

def do_something(a, proc)
  puts proc.call(a)
end

five_multiple = Proc.new {|b| b * 5}

do_something(2, five_multiple)

puts "\n** passing in multiple procs **"

def do_something(proc1, proc2)
  proc1.call
  proc2.call
end

proc1 = Proc.new {puts "proc1!"}
proc2 = Proc.new {puts "proc2!"}

do_something(proc1, proc2)

puts "\n** Convert block to a proc **"
def do_something(&block)
  block.call
end

do_something {puts "something is happening"}

# output:
# Proc
# 10
#
# ** passing in multiple procs **
# proc1!
# proc2!
#
# ** Convert block to a proc **
# something is happening
