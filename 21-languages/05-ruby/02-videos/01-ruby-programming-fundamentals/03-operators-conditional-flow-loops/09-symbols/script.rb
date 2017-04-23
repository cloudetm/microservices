# Symbols - CONST

puts "** Use string for keys **"
hash1 = {"string" => "value"}
hash2 = {"string" => "value"}

puts "** Use symbol for keys to save memory **"

hash1 = {:symbol => "value"}
hash2 = {symbol: "value"}

puts "** symbols **"
puts :hello.class # Symbol
puts "hello".class # String

puts :hello.upcase # HELLO
puts :Hello.downcase # hello
puts :hello.capitalize # Hello

puts "** assign a value to CONST is NOT allowed **"
# :hello = "hi" # syntax error, unexpected '=', expecting end-of-input (SyntaxError)

puts "** object reference - different object ids **"
puts "phil".object_id # 70319457029160
puts "phil".object_id # 70319457029100

puts "** Symbol reference - same object id **"
puts :phil.object_id # 406728
puts :phil.object_id # 406728

puts "** map - use symbol **"
person = {:name => "Joe"}
person = {name: "Joe"}
puts person[:name] # Joe
puts person.keys # name

puts "** use symbol as predicates **"
people = ["Tom", "Dick", "Harry"]
puts people.send(:pop) # Harry
puts people.respond_to?(:pop) # true
puts people.respond_to?(:pop2) # false
