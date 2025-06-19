graph [
  directed 1
  multigraph 1
  label "CFG of MergeSort.java"
  type "Control Flow Graph (CFG)"
  file "MergeSort.java"
  package ""

  node [
    id 0
    line 3
    label "double[] merge(double[] a, double[] b)"
  ]
  node [
    id 1
    line 4
    label "double[] c = new double[a.length + b.length]"
  ]
  node [
    id 2
    line 5
    label "int i = 0, j = 0"
  ]
  node [
    id 3
    line 6
    label "int k = 0"
  ]
  node [
    id 4
    line 6
    label "for (k < c.length)"
  ]
  node [
    id 5
    line 6
    label "k++"
  ]
  node [
    id 6
    line 0
    label "endfor"
  ]
  node [
    id 7
    line 7
    label "if (i >= a.length)"
  ]
  node [
    id 8
    line 7
    label "c[k] = b[j++]"
  ]
  node [
    id 9
    line 0
    label "endif"
  ]
  node [
    id 10
    line 8
    label "if (j >= b.length)"
  ]
  node [
    id 11
    line 8
    label "c[k] = a[i++]"
  ]
  node [
    id 12
    line 0
    label "endif"
  ]
  node [
    id 13
    line 9
    label "if (a[i] <= b[j])"
  ]
  node [
    id 14
    line 9
    label "c[k] = a[i++]"
  ]
  node [
    id 15
    line 0
    label "endif"
  ]
  node [
    id 16
    line 10
    label "c[k] = b[j++]"
  ]
  node [
    id 17
    line 12
    label "return c;"
  ]
  node [
    id 18
    line 15
    label "double[] mergesort(double[] input)"
  ]
  node [
    id 19
    line 16
    label "int N = input.length"
  ]
  node [
    id 20
    line 17
    label "if (N <= 1)"
  ]
  node [
    id 21
    line 17
    label "return input;"
  ]
  node [
    id 22
    line 0
    label "endif"
  ]
  node [
    id 23
    line 18
    label "double[] a = new double[N/2]"
  ]
  node [
    id 24
    line 19
    label "double[] b = new double[N - N/2]"
  ]
  node [
    id 25
    line 20
    label "int i = 0"
  ]
  node [
    id 26
    line 20
    label "for (i < a.length)"
  ]
  node [
    id 27
    line 20
    label "i++"
  ]
  node [
    id 28
    line 0
    label "endfor"
  ]
  node [
    id 29
    line 21
    label "a[i] = input[i]"
  ]
  node [
    id 30
    line 22
    label "int i = 0"
  ]
  node [
    id 31
    line 22
    label "for (i < b.length)"
  ]
  node [
    id 32
    line 22
    label "i++"
  ]
  node [
    id 33
    line 0
    label "endfor"
  ]
  node [
    id 34
    line 23
    label "b[i] = input[i + N/2]"
  ]
  node [
    id 35
    line 24
    label "return merge(mergesort(a), mergesort(b));"
  ]
  node [
    id 36
    line 27
    label "boolean isSorted(double[] a)"
  ]
  node [
    id 37
    line 28
    label "int i = 1"
  ]
  node [
    id 38
    line 28
    label "for (i < a.length)"
  ]
  node [
    id 39
    line 28
    label "i++"
  ]
  node [
    id 40
    line 0
    label "endfor"
  ]
  node [
    id 41
    line 29
    label "if (a[i] < a[i-1])"
  ]
  node [
    id 42
    line 29
    label "return false;"
  ]
  node [
    id 43
    line 0
    label "endif"
  ]
  node [
    id 44
    line 30
    label "return true;"
  ]

  edge [
    id 0
    source 0
    target 1
    label ""
  ]
  edge [
    id 1
    source 1
    target 2
    label ""
  ]
  edge [
    id 2
    source 2
    target 3
    label ""
  ]
  edge [
    id 3
    source 3
    target 4
    label ""
  ]
  edge [
    id 4
    source 4
    target 6
    label "False"
  ]
  edge [
    id 5
    source 4
    target 7
    label "True"
  ]
  edge [
    id 6
    source 7
    target 8
    label "True"
  ]
  edge [
    id 7
    source 8
    target 9
    label ""
  ]
  edge [
    id 8
    source 7
    target 10
    label "False"
  ]
  edge [
    id 9
    source 10
    target 11
    label "True"
  ]
  edge [
    id 10
    source 11
    target 12
    label ""
  ]
  edge [
    id 11
    source 10
    target 13
    label "False"
  ]
  edge [
    id 12
    source 13
    target 14
    label "True"
  ]
  edge [
    id 13
    source 14
    target 15
    label ""
  ]
  edge [
    id 14
    source 13
    target 16
    label "False"
  ]
  edge [
    id 15
    source 16
    target 15
    label ""
  ]
  edge [
    id 16
    source 15
    target 12
    label ""
  ]
  edge [
    id 17
    source 12
    target 9
    label ""
  ]
  edge [
    id 18
    source 9
    target 5
    label ""
  ]
  edge [
    id 19
    source 5
    target 4
    label ""
  ]
  edge [
    id 20
    source 6
    target 17
    label ""
  ]
  edge [
    id 21
    source 18
    target 19
    label ""
  ]
  edge [
    id 22
    source 19
    target 20
    label ""
  ]
  edge [
    id 23
    source 20
    target 21
    label "True"
  ]
  edge [
    id 24
    source 20
    target 22
    label "False"
  ]
  edge [
    id 25
    source 22
    target 23
    label ""
  ]
  edge [
    id 26
    source 23
    target 24
    label ""
  ]
  edge [
    id 27
    source 24
    target 25
    label ""
  ]
  edge [
    id 28
    source 25
    target 26
    label ""
  ]
  edge [
    id 29
    source 26
    target 28
    label "False"
  ]
  edge [
    id 30
    source 26
    target 29
    label "True"
  ]
  edge [
    id 31
    source 29
    target 27
    label ""
  ]
  edge [
    id 32
    source 27
    target 26
    label ""
  ]
  edge [
    id 33
    source 28
    target 30
    label ""
  ]
  edge [
    id 34
    source 30
    target 31
    label ""
  ]
  edge [
    id 35
    source 31
    target 33
    label "False"
  ]
  edge [
    id 36
    source 31
    target 34
    label "True"
  ]
  edge [
    id 37
    source 34
    target 32
    label ""
  ]
  edge [
    id 38
    source 32
    target 31
    label ""
  ]
  edge [
    id 39
    source 33
    target 35
    label ""
  ]
  edge [
    id 40
    source 36
    target 37
    label ""
  ]
  edge [
    id 41
    source 37
    target 38
    label ""
  ]
  edge [
    id 42
    source 38
    target 40
    label "False"
  ]
  edge [
    id 43
    source 38
    target 41
    label "True"
  ]
  edge [
    id 44
    source 41
    target 42
    label "True"
  ]
  edge [
    id 45
    source 41
    target 43
    label "False"
  ]
  edge [
    id 46
    source 43
    target 39
    label ""
  ]
  edge [
    id 47
    source 39
    target 38
    label ""
  ]
  edge [
    id 48
    source 40
    target 44
    label ""
  ]
]
