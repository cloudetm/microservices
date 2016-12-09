car_1 = "blue"
car_2 = "white"

if car_1 == "blue" || car_2 == "blue"
  puts "found blue car"
else
  puts "car_1 or car_2 is not blue"
end

if car_1 == "blue" && car_2 == "blue"
  puts "found blue cars"
else
  puts "car_1 and car_2 are not blue"
end

# output:
# found blue car
# car_1 and car_2 are not blue
