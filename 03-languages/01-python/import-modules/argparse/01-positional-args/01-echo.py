import argparse
parser = argparse.ArgumentParser()
parser.add_argument("echo", help="each the string you use here")  # positional arguments
args = parser.parse_args()
print args.echo
