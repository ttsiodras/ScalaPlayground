#!/usr/bin/env python

def main():
    maxSize = 4480
    fileSizes = xrange(1,100)

    # Time for dynamic programming

    optimalResults = {}
    lastStep = {}

    # Line: from 0 .. maxSize
    for containerSize in xrange(0, maxSize+1):  
        # Column: enumerate gives tuple of (index, file size)
        for idx,fileSize in enumerate(fileSizes): 
            # We will index the "optimalResults" via tuples:
            # The current cell
            cellCurrent = (containerSize, idx)
            # The cell on our left
            cellOnTheLeftOfCurrent = (containerSize, idx-1)
            # Does the file on column "idx" fit inside containerSize?
            if containerSize<fileSize: 
                # the file in column idx doesn't fit in containerSize
                # so copy optimal value from the cell on its left
                optimalResults[cellCurrent] =  optimalResults.get(cellOnTheLeftOfCurrent,0)
                # Copy last step from cell on the left...
                lastStep[cellCurrent] = lastStep.get(cellOnTheLeftOfCurrent,0)
            else:
                # It fits!
                # Using file of column "idx", the remaining space is...
                remainingSpace = containerSize - fileSize
                # and for that remaining space, the optimal result 
                # using the first idx-1 files has been found to be:
                optimalResultOfRemainingSpace = optimalResults.get((remainingSpace, idx-1),0)
                # so let's check: do we improve things by using "idx"?
                if optimalResultOfRemainingSpace + fileSize > optimalResults.get(cellOnTheLeftOfCurrent,0):
                    # we improved the best result, store it!
                    optimalResults[cellCurrent] = optimalResultOfRemainingSpace + fileSize
                    # Store last step - i.e. using file "idx"
                    lastStep[cellCurrent] = fileSize
                else:
                    # no improvement by using "idx" - copy from left...
                    optimalResults[cellCurrent] = optimalResults[cellOnTheLeftOfCurrent]
                    # Copy last step from cell on the left...
                    lastStep[cellCurrent] = lastStep.get(cellOnTheLeftOfCurrent,0)


    print "Attainable:", optimalResults[(maxSize, len(fileSizes)-1)]

    # Navigate backwards... starting from the optimal result:
    total = optimalResults[(maxSize, len(fileSizes)-1)]

    while total>0:
        # The last step we used to get to "total" was...
        lastFileSize = lastStep[(total, len(fileSizes)-1)]
        print total, "removing", lastFileSize

        # And the one before the last step was... (loop)
        total -= lastFileSize

if __name__ == "__main__":
    main()
