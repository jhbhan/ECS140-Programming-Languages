import Data.List
import System.IO

--without pattern matching
myremoveduplicates mylist | null mylist = mylist | elem (head mylist) (tail mylist) = myremoveduplicates (tail mylist) | otherwise = (head mylist):myremoveduplicates (tail mylist)

--with pattern matching

--without pattern matching
myintersection mylist1 mylist2 | null mylist1 || null mylist2 = [] | elem (head mylist1) mylist2 = (head mylist1):(myintersection (tail mylist1) mylist2)|otherwise = myintersection (tail mylist1) mylist2

--without pattern matching
mylast mylist | length mylist == 1 = mylist | otherwise = mylast (tail mylist)

--without pattern matching
myreverse mylist | null mylist  = []| otherwise = myreverse (tail mylist) ++ [(head mylist)]

--without pattern matching	
myreplaceall x y mylist| null mylist = []| y == (head mylist) = x:myreplaceall x y (tail mylist) | otherwise = (head mylist):myreplaceall x y (tail mylist)