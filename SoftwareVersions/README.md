Implement comparison of two software version numbers which returns a number indicating the result of the comparison.

Software version numbers should be validated and are required to contain only numbers and/or the period sign “." (but only if the sign is preceded and followed by numbers). Invalid or null version numbers should make the method throw an IllegalArgumentException. The period separates the number groups in the version and are compared left to right (1.0 < 1.0.1 < 1.1 < 1.2.1 < 2.0). Version numbers can have a maximum of three groups but can also have only one or two (“1”, “1.0”, “1.0.0” are all valid versions and refer to the same number). 

## return value:
* if v1 < v2 return -1
* if v1 == v2 return 0
* if v1 > v2 return 1

## Examples of use:
* compareVersions("1", "1.1.0") = -1
* compareVersions(null, “1.1”) —> throws IllegalArgumentException
* compareVersions(“1.0”, “2.”) —> throws IllegalArgumentException
* compareVersions(“2.1.a”, “3”) —> throws IllegalArgumentException
* compareVersions("1", "1.0") = 0
* compareVersions("1.2.5", "1.2.4") = 1
