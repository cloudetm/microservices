arr = [1]
puts arr.empty?

class LightSwitch
  attr_accessor :flip_state

  def on?
    @flip_state
  end
end

l = LightSwitch.new
puts l.on? # nothing

if l.on? # it is nil, so l.on? is false
  puts "I'm on 1"
end

l.flip_state = true
if l.on?
  puts "I'm on 2" # I'm on 2
end

l.flip_state = "on"
if l.on?
  puts "I'm on 3" # I'm on 3
end

l.flip_state = false
if l.on?
  puts "I'm not nil"
else
  puts "I'm false" # I'm false
end
