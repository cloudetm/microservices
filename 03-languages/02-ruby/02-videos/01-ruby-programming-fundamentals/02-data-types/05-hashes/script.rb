months = Hash.new "month"
puts months.length # 0
puts months[0] # month

H = Hash["a" => 100, "b" => 200]
puts H.length # 2
puts H["a"] # 100
puts H["b"] # 200
puts "{#{H[18]}}" # {}
puts H.keys # a b
puts H.values # 100, 200
H["a"] = 10
puts H.values # 10 ,200

puts months.empty? # true
puts H.empty? # false

puts "** new_hash **"
new_hash = Hash.new
puts new_hash["one"]=100 # 100
puts new_hash.empty? # false
new_hash["two"]=200
new_hash["three"]=300
puts new_hash.length # 3

puts new_hash.delete("two") # 200

puts new_hash.keys # one, three
puts new_hash.values # 100, 300
puts new_hash.key?("three") # true
puts new_hash.key?("two") # false
puts new_hash.value?(200) # false
puts new_hash.inspect # {"one"=>100, "three"=>300}
puts new_hash.to_s # {"one"=>100, "three"=>300}
puts new_hash.to_a # one 100, three 300
