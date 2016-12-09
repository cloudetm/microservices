# http://rubylearning.com/satishtalim/ruby_exceptions.html

begin
  puts 1 + "1"
  # :hello = "hi"
rescue Exception => e
  puts e.message
  puts "RESCUED!"
end

# output:
# String can't be coerced into Fixnum
# RESCUED!
