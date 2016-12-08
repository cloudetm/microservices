my_string = "hi, test string"
puts my_string.split # `'hi,' 'test' 'string'` - by default, it splits empty space
puts my_string.split(',') # `'hi' 'test stirng'` - it splits ","

my_date = "1/1/2016"
puts my_date.split('/') # `'1' '1' '2016'` - it splits "/"
