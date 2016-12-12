import argparse
parser = argparse.ArgumentParser()
parser.add_argument('-t', dest="type", choices=["library", "service"],
                    required=True, help="Which project type to create")
args = parser.parse_args()
if args.type:
    print args.type
