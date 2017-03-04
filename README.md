#fileio
##By Noppawan Kulchol (5910545728)
###The task and record the elapse time
| Task | Elapse Time |
| ----------------------- | ------------------ |
| Copy file one byte at a time | 8.296668 sec |
| Copy the file using a byte array of size 1024KB | 0.012392 sec |
| Copy the file using a byte array of size 4096KB | 0.004405 sec |
| Copy the file using a byte array of size 6553KB | 0.002703 sec |
| Copy the file using BufferedReader and PrintWriter to copy lines of text(bcopy) | 0.117189 sec |

###Why some copy methods are faster and slower than others
First copy method, copy file one byte at time is the slowest because each byte are read that spend a lot of time until the end of file. Second copy method, copy file using a byte of array is the fastest because it reads size of byte array. And bcopy methods, copy file using BufferedReader and PrintWriter to copy lines of text is slower than second copy method because BufferedReader reads the file just each one line and change Byte > byte[] > char > char[] > String. So, it spends time more than second copy method.
