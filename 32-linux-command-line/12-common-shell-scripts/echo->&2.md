# echo >&2

http://stackoverflow.com/questions/23489934/echo-2-some-text-what-does-it-mean-in-shell-scripting

To quickly explain what the others missed:

`echo "hey" >&2`

`>` redirect standard output (implicit 1>)

`&` what comes next is a file descriptor, not a file (only for right hand side of >)

`2` stderr file descriptor number

Redirect stdout from echo command to stderr. (If you were to useecho "hey" >2 you would output hey to a file called 2)
