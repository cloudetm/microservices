country = "United States"
configuration = "both"

case configuration
  when "reverse"
    puts "This is the country backwards"
    puts country.reverse
  when "uppercase"
    puts "This is the country in all uppercase letters"
    puts country.upcase
  when "both"
    puts "This is using both"
    puts country.reverse.upcase
  else
    puts "Sorry - that isn't an option"
end

# output:
# This is using both
# SETATS DETINU
