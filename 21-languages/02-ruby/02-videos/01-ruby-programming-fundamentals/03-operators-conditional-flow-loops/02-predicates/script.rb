puts "** numbers **"

puts 3.odd? # true
puts 3.even? # false
puts 1.integer? # true
puts 2.0.integer? # false
puts 0.zero? # true
puts 1.zero? # false

puts "** between? **"

puts 3.between?(2,3) # true
puts 3.between?(4,10) # false

puts "** start_with? **"
name = "John Doe"
puts name.start_with?("J") # true
puts name.start_with?("j") # false
puts name.start_with?("M") # false

puts "** includes? **"
puts [1,2].include?(1) # true
puts [1,2].include?(5) # false

puts "** hash **"
puts key1 = {"key1" => "value1"}.key?("key1") # true
puts key1 = {"key1" => "value1"}.key?("key2") # false

puts "** arrays **"
arr = []
puts arr.empty? # true
puts arr.nil? # false

puts "** list all predicates **"
print 8.methods.sort, "\n" # [:!, :!=, :!~, :%, :&, :*, :**, :+, :+@, :-, :-@, :/, :<, :<<, :<=, :<=>, :==, :===, :=~,
# :>, :>=, :>>, :[], :^, :__id__, :__send__, :abs, :abs2, :angle, :arg, :between?, :ceil, :chr, :class, :clone, :coerce,
# :conj, :conjugate, :define_singleton_method, :denominator, :display, :div, :divmod, :downto, :dup, :enum_for, :eql?,
# :equal?, :even?, :extend, :fdiv, :floor, :freeze, :frozen?, :gcd, :gcdlcm, :hash, :i, :imag, :imaginary, :inspect,
# :instance_eval, :instance_exec, :instance_of?, :instance_variable_defined?, :instance_variable_get, :instance_variable_set,
# :instance_variables, :integer?, :is_a?, :kind_of?, :lcm, :magnitude, :method, :methods, :modulo, :next, :nil?, :nonzero?,
# :numerator, :object_id, :odd?, :ord, :phase, :polar, :pred, :private_methods, :protected_methods, :public_method,
# :public_methods, :public_send, :quo, :rationalize, :real, :real?, :rect, :rectangular, :remainder, :remove_instance_variable,
# :respond_to?, :round, :send, :singleton_class, :singleton_method_added, :singleton_methods, :size, :step, :succ, :taint,
# :tainted?, :tap, :times, :to_c, :to_enum, :to_f, :to_i, :to_int, :to_r, :to_s, :truncate, :trust, :untaint, :untrust,
# :untrusted?, :upto, :zero?, :|, :~]
