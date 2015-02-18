"""
Merge function for 2048 game.
http://www.codeskulptor.org/#user39_7M4J8Bj2Th_2.py
"""

def merge(line):
    """
    Function that merges a single row or column in 2048.
    """
    # replace with your code
    mline = []
    for index in range (0, len(line)):
        if line[index] != 0:
            mline.append(line[index])
    
    while (len(mline) < len(line)):
        mline.append(0)
    
    
    for index in range(0, len(mline)-1):
        if mline[index] == mline[index+1]:
            mline[index] *= 2
            mline.pop(index+1)
            mline.append(0)
    
            
    return mline