import Data.List
import System.IO

--without pattern matching
--myremoveduplicates :: Eq a => [a] -> [a]
myremoveduplicates mylist | null mylist = mylist | elem (head mylist) (tail mylist) = myremoveduplicates (tail mylist) | otherwise = (head mylist):myremoveduplicates (tail mylist)

--with pattern matching
--myremoveduplicates_pm :: Eq a => [a] -> [a]
myremoveduplicates_pm [] = []
myremoveduplicates_pm (x:[]) = [x]
myremoveduplicates_pm (x:y:[]) = if x == y then [x] else x:y:[]
myremoveduplicates_pm (x:xs)| elem x xs = myremoveduplicates_pm xs | otherwise = x:(myremoveduplicates_pm xs)


--without pattern matching
--myintersection :: (Foldable t, Eq a) => [a] -> t a -> [a]
myintersection mylist1 mylist2 | null mylist1 || null mylist2 = [] | elem (head mylist1) mylist2 = (head mylist1):(myintersection (tail mylist1) mylist2)|otherwise = myintersection (tail mylist1) mylist2
--with pattern matching
--myintersection_pm :: Eq a => [a] -> [a] -> [a]
myintersection_pm x [] = []
myintersection_pm [] y = []
myintersection_pm (x:xs) y |elem x y = x:(myintersection_pm xs y) |otherwise = myintersection_pm xs y



--without pattern matching
--mylast :: Eq a => [a] -> [a]
mylast mylist | null mylist = mylist | (tail mylist) == [] = mylist | otherwise = mylast (tail mylist)
--with pattern 
--mylast_pm :: [a] -> [a]
mylast_pm [] = []
mylast_pm (x:[]) = [x]
mylast_pm (x:y:[]) = [y]
mylast_pm (x:xs) = mylast_pm xs

--without patternmatching
--myreverse :: Eq a => [a] -> [a]
myreverse mylist = myreversehelp mylist [] where
myreversehelp mylist reversed |null mylist = mylist| (tail mylist) == [] = (head mylist):reversed|otherwise = myreversehelp (tail mylist) ((head mylist):reversed)
--with pattern matching
--myreverse_pm :: Eq a => [a] -> [a]
myreverse_pm mylist = myreversehelp mylist [] where
myreversehelp_pm [] reversed = reversed
myreversehelp_pm (x:xs) reversed = myreversehelp_pm xs (x:reversed)



--without pattern matching
--myreplaceall :: Eq a => a -> a -> [a] -> [a]
myreplaceall x y mylist| null mylist = []| y == (head mylist) = x:myreplaceall x y (tail mylist) | otherwise = (head mylist):myreplaceall x y (tail mylist)

--with pattern matching
--myreplaceall_pm :: Eq a => a -> a -> [a] -> [a]
myreplaceall_pm x y [] = []
myreplaceall_pm x y (z:[]) = if z == y then [x] else [z]
myreplaceall_pm x y (z:w:[]) = if z == y then x:(myreplaceall_pm x y [w]) else z:(myreplaceall_pm x y [w])
myreplaceall_pm x y (z:zs) = if z == y then x:(myreplaceall_pm x y zs) else z:(myreplaceall_pm x y zs)
