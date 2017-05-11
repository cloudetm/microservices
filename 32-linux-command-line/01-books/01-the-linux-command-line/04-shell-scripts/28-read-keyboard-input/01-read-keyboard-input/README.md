# Reading Keyboard Input

`interactivity` the ability of the program to interact with the user.

## read - Read Values from Standard Input

The `read` built-in command is used to read a single line of standard input.

```
read [-options] [variable...]
```

> read Options

| Option       | Description |
|--------------|-------------|
| -a array     | Assign the input to array, starting with index zero |
| -d delimiter | The first character in the string delimiter is used to indicate end of input, rather than a newline character |
| -e           | Use Readline to handle input. This permits input editing in the same manner as the command line |
| -n num       | Read num characters of input, rather than an entire line |
| -p prompt    | Display a prompt for input using the string prompt |
| -r           | Raw mode. Do not interpret backslash characters as escapes |
| -s           | Silent mode. Do not echo characters to the display as they are typed. Useful for password |
| -t seconds   | Timeout. Terminate input after seconds. read returns a non-zero exit status if an input times out |
| -u fd        | Use input from file descriptor fd, rather than standard input |

