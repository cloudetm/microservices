o = Object.new

def o.my_method
  1 + 1
end

puts o.my_method # 2

class Car
  def start_car
    puts "Car has started"
  end
end

c = Car.new

def c.stop_car
  puts "Car has stopped"
end

puts c.start_car
puts c.stop_car

# output:
# 2
# Car has started
#
# Car has stopped
