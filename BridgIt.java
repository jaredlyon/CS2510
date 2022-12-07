Tester Library v.3.0
-----------------------------------
Tests defined in the class: ExamplesBridgIt:
---------------------------
ExamplesBridgIt:
---------------
new ExamplesBridgIt:1(
 this.node1 = new Node:2(
  this.color = [r=255,g=175,b=175]
  this.up = null
  this.down = null
  this.left = null
  this.right = null
  this.changed = true)
 this.node2 = new Node:3(
  this.color = [r=255,g=0,b=255]
  this.up = null
  this.down = null
  this.left = null
  this.right = null
  this.changed = true)
 this.empty1 = new Empty:4(
  this.color = [r=255,g=255,b=255]
  this.up = null
  this.down = null
  this.left = null
  this.right = null
  this.changed = false)
 this.edge1 = new Edge:5(
  this.color = [r=255,g=0,b=0]
  this.changed = true)
 this.game1 = new BridgIt:6(
  this.size = 11
  this.nodes = new java.util.ArrayList:7(){
   Iterable[0] new java.util.ArrayList:8(){
    Iterable[0] new Empty:9(
     this.color = [r=255,g=255,b=255]
     this.up = new Edge:10(
      this.color = [r=255,g=0,b=0]
      this.changed = true)
     this.down = new Node:11(
      this.color = [r=255,g=0,b=255]
      this.up = Empty:9
      this.down = new Empty:12(
       this.color = [r=255,g=255,b=255]
       this.up = Node:11
       this.down = new Node:13(
        this.color = [r=255,g=0,b=255]
        this.up = Empty:12
        this.down = new Empty:14(
         this.color = [r=255,g=255,b=255]
         this.up = Node:13
         this.down = new Node:15(
          this.color = [r=255,g=0,b=255]
          this.up = Empty:14
          this.down = new Empty:16(
           this.color = [r=255,g=255,b=255]
           this.up = Node:15
           this.down = new Node:17(
            this.color = [r=255,g=0,b=255]
            this.up = Empty:16
            this.down = new Empty:18(
             this.color = [r=255,g=255,b=255]
             this.up = Node:17
             this.down = new Node:19(
              this.color = [r=255,g=0,b=255]
              this.up = Empty:18
              this.down = new Empty:20(
               this.color = [r=255,g=255,b=255]
               this.up = Node:19
               this.down = new Edge:21(
                this.color = [r=255,g=0,b=0]
                this.changed = true)
               this.left = new Edge:22(
                this.color = [r=255,g=0,b=0]
                this.changed = true)
               this.right = new Node:23(
                this.color = [r=255,g=175,b=175]
                this.up = new Empty:24(
                 this.color = [r=255,g=255,b=255]
                 this.up = new Node:25(
                  this.color = [r=255,g=175,b=175]
                  this.up = new Empty:26(
                   this.color = [r=255,g=255,b=255]
                   this.up = new Node:27(
                    this.color = [r=255,g=175,b=175]
                    this.up = new Empty:28(
                     this.color = [r=255,g=255,b=255]
                     this.up = new Node:29(
                      this.color = [r=255,g=175,b=175]
                      this.up = new Empty:30(
                       this.color = [r=255,g=255,b=255]
                       this.up = new Node:31(
                        this.color = [r=255,g=175,b=175]
                        this.up = new Empty:32(
                         this.color = [r=255,g=255,b=255]
                         this.up = new Node:33(
                          this.color = [r=255,g=175,b=175]
                          this.up = new Edge:34(
                           this.color = [r=255,g=0,b=0]
                           this.changed = true)
                          this.down = Empty:32
                          this.left = Empty:9
                          this.right = new Empty:35(
                           this.color = [r=255,g=255,b=255]
                           this.up = new Edge:36(
                            this.color = [r=255,g=0,b=0]
                            this.changed = true)
                           this.down = new Node:37(
                            this.color = [r=255,g=0,b=255]
                            this.up = Empty:35
                            this.down = new Empty:38(
                             this.color = [r=255,g=255,b=255]
                             this.up = Node:37
                             this.down = new Node:39(
                              this.color = [r=255,g=0,b=255]
                              this.up = Empty:38
                              this.down = new Empty:40(
                               this.color = [r=255,g=255,b=255]
                               this.up = Node:39
                               this.down = new Node:41(
                                this.color = [r=255,g=0,b=255]
                                this.up = Empty:40
                                this.down = new Empty:42(
                                 this.color = [r=255,g=255,b=255]
                                 this.up = Node:41
                                 this.down = new Node:43(
                                  this.color = [r=255,g=0,b=255]
                                  this.up = Empty:42
                                  this.down = new Empty:44(
                                   this.color = [r=255,g=255,b=255]
                                   this.up = Node:43
                                   this.down = new Node:45(
                                    this.color = [r=255,g=0,b=255]
                                    this.up = Empty:44
                                    this.down = new Empty:46(
                                     this.color = [r=255,g=255,b=255]
                                     this.up = Node:45
                                     this.down = new Edge:47(
                                      this.color = [r=255,g=0,b=0]
                                      this.changed = true)
                                     this.left = Node:23
                                     this.right = new Node:48(
                                      this.color = [r=255,g=175,b=175]
                                      this.up = new Empty:49(
                                       this.color = [r=255,g=255,b=255]
                                       this.up = new Node:50(
                                        this.color = [r=255,g=175,b=175]
                                        this.up = new Empty:51(
                                         this.color = [r=255,g=255,b=255]
                                         this.up = new Node:52(
                                          this.color = [r=255,g=175,b=175]
                                          this.up = new Empty:53(
                                           this.color = [r=255,g=255,b=255]
                                           this.up = new Node:54(
                                            this.color = [r=255,g=175,b=175]
                                            this.up = new Empty:55(
                                             this.color = [r=255,g=255,b=255]
                                             this.up = new Node:56(
                                              this.color = [r=255,g=175,b=175]
                                              this.up = new Empty:57(
                                               this.color = [r=255,g=255,b=255]
                                               this.up = new Node:58(
                                                this.color = [r=255,g=175,b=175]
                                                this.up = new Edge:59(
                                                 this.color = [r=255,g=0,b=0]
                                                 this.changed = true)
                                                this.down = Empty:57
                                                this.left = Empty:35
                                                this.right = new Empty:60(
                                                 this.color = [r=255,g=255,b=255]
                                                 this.up = new Edge:61(
                                                  this.color = [r=255,g=0,b=0]
                                                  this.changed = true)
                                                 this.down = new Node:62(
                                                  this.color = [r=255,g=0,b=255]
                                                  this.up = Empty:60
                                                  this.down = new Empty:63(
                                                   this.color = [r=255,g=255,b=255]
                                                   this.up = Node:62
                                                   this.down = new Node:64(
                                                    this.color = [r=255,g=0,b=255]
                                                    this.up = Empty:63
                                                    this.down = new Empty:65(
                                                     this.color = [r=255,g=255,b=255]
                                                     this.up = Node:64
                                                     this.down = new Node:66(
                                                      this.color = [r=255,g=0,b=255]
                                                      this.up = Empty:65
                                                      this.down = new Empty:67(
                                                       this.color = [r=255,g=255,b=255]
                                                       this.up = Node:66
                                                       this.down = new Node:68(
                                                        this.color = [r=255,g=0,b=255]
                                                        this.up = Empty:67
                                                        this.down = new Empty:69(
                                                         this.color = [r=255,g=255,b=255]
                                                         this.up = Node:68
                                                         this.down = new Node:70(
                                                          this.color = [r=255,g=0,b=255]
                                                          this.up = Empty:69
                                                          this.down = new Empty:71(
                                                           this.color = [r=255,g=255,b=255]
                                                           this.up = Node:70
                                                           this.down = new Edge:72(
                                                            this.color = [r=255,g=0,b=0]
                                                            this.changed = true)
                                                           this.left = Node:48
                                                           this.right = new Node:73(
                                                            this.color = [r=255,g=175,b=175]
                                                            this.up = new Empty:74(
                                                             this.color = [r=255,g=255,b=255]
                                                             this.up = new Node:75(
                                                              this.color = [r=255,g=175,b=175]
                                                              this.up = new Empty:76(
                                                               this.color = [r=255,g=255,b=255]
                                                               this.up = new Node:77(
                                                                this.color = [r=255,g=175,b=175]
                                                                this.up = new Empty:78(
                                                                 this.color = [r=255,g=255,b=255]
                                                                 this.up = new Node:79(
                                                                  this.color = [r=255,g=175,b=175]
                                                                  this.up = new Empty:80(
                                                                   this.color = [r=255,g=255,b=255]
                                                                   this.up = new Node:81(
                                                                    this.color = [r=255,g=175,b=175]
                                                                    this.up = new Empty:82(
                                                                     this.color = [r=255,g=255,b=255]
                                                                     this.up = new Node:83(
                                                                      this.color = [r=255,g=175,b=175]
                                                                      this.up = new Edge:84(
                                                                       this.color = [r=255,g=0,b=0]
                                                                       this.changed = true)
                                                                      this.down = Empty:82
                                                                      this.left = Empty:60
                                                                      this.right = new Empty:85(
                                                                       this.color = [r=255,g=255,b=255]
                                                                       this.up = new Edge:86(
                                                                        this.color = [r=255,g=0,b=0]
                                                                        this.changed = true)
                                                                       this.down = new Node:87(
                                                                        this.color = [r=255,g=0,b=255]
                                                                        this.up = Empty:85
                                                                        this.down = new Empty:88(
                                                                         this.color = [r=255,g=255,b=255]
                                                                         this.up = Node:87
                                                                         this.down = new Node:89(
                                                                          this.color = [r=255,g=0,b=255]
                                                                          this.up = Empty:88
                                                                          this.down = new Empty:90(
                                                                           this.color = [r=255,g=255,b=255]
                                                                           this.up = Node:89
                                                                           this.down = new Node:91(
                                                                            this.color = [r=255,g=0,b=255]
                                                                            this.up = Empty:90
                                                                            this.down = new Empty:92(
                                                                             this.color = [r=255,g=255,b=255]
                                                                             this.up = Node:91
                                                                             this.down = new Node:93(
                                                                              this.color = [r=255,g=0,b=255]
                                                                              this.up = Empty:92
                                                                              this.down = new Empty:94(
                                                                               this.color = [r=255,g=255,b=255]
                                                                               this.up = Node:93
                                                                               this.down = new Node:95(
                                                                                this.color = [r=255,g=0,b=255]
                                                                                this.up = Empty:94
                                                                                this.down = new Empty:96(
                                                                                 this.color = [r=255,g=255,b=255]
                                                                                 this.up = Node:95
                                                                                 this.down = new Edge:97(
                                                                                  this.color = [r=255,g=0,b=0]
                                                                                  this.changed = true)
                                                                                 this.left = Node:73
                                                                                 this.right = new Node:98(
                                                                                  this.color = [r=255,g=175,b=175]
                                                                                  this.up = new Empty:99(
                                                                                   this.color = [r=255,g=255,b=255]
                                                                                   this.up = new Node:100(
                                                                                    this.color = [r=255,g=175,b=175]
                                                                                    this.up = new Empty:101(
                                                                                     this.color = [r=255,g=255,b=255]
                                                                                     this.up = new Node:102(
                                                                                      this.color = [r=255,g=175,b=175]
                                                                                      this.up = new Empty:103(
                                                                                       this.color = [r=255,g=255,b=255]
                                                                                       this.up = new Node:104(
                                                                                        this.color = [r=255,g=175,b=175]
                                                                                        this.up = new Empty:105(
                                                                                         this.color = [r=255,g=255,b=255]
                                                                                         this.up = new Node:106(
                                                                                          this.color = [r=255,g=175,b=175]
                                                                                          this.up = new Empty:107(
                                                                                           this.color = [r=255,g=255,b=255]
                                                                                           this.up = new Node:108(
                                                                                            this.color = [r=255,g=175,b=175]
                                                                                            this.up = new Edge:109(
                                                                                             this.color = [r=255,g=0,b=0]
                                                                                             this.changed = true)
                                                                                            this.down = Empty:107
                                                                                            this.left = Empty:85
                                                                                            this.right = new Empty:110(
                                                                                             this.color = [r=255,g=255,b=255]
                                                                                             this.up = new Edge:111(
                                                                                              this.color = [r=255,g=0,b=0]
                                                                                              this.changed = true)
                                                                                             this.down = new Node:112(
                                                                                              this.color = [r=255,g=0,b=255]
                                                                                              this.up = Empty:110
                                                                                              this.down = new Empty:113(
                                                                                               this.color = [r=255,g=255,b=255]
                                                                                               this.up = Node:112
                                                                                               this.down = new Node:114(
                                                                                                this.color = [r=255,g=0,b=255]
                                                                                                this.up = Empty:113
                                                                                                this.down = new Empty:115(
                                                                                                 this.color = [r=255,g=255,b=255]
                                                                                                 this.up = Node:114
                                                                                                 this.down = new Node:116(
                                                                                                  this.color = [r=255,g=0,b=255]
                                                                                                  this.up = Empty:115
                                                                                                  this.down = new Empty:117(
                                                                                                   this.color = [r=255,g=255,b=255]
                                                                                                   this.up = Node:116
                                                                                                   this.down = new Node:118(
                                                                                                    this.color = [r=255,g=0,b=255]
                                                                                                    this.up = <truncated; objects are too deeply nested to print>
                                                                                                    this.down = <truncated; objects are too deeply nested to print>
                                                                                                    this.left = <truncated; objects are too deeply nested to print>
                                                                                                    this.right = <truncated; objects are too deeply nested to print>
                                                                                                    this.changed = true)
                                                                                                   this.left = Node:102
                                                                                                   this.right = new Node:119(
                                                                                                    this.color = [r=255,g=175,b=175]
                                                                                                    this.up = <truncated; objects are too deeply nested to print>
                                                                                                    this.down = <truncated; objects are too deeply nested to print>
                                                                                                    this.left = <truncated; objects are too deeply nested to print>
                                                                                                    this.right = <truncated; objects are too deeply nested to print>
                                                                                                    this.changed = true)
                                                                                                   this.changed = false)
                                                                                                  this.left = Empty:103
                                                                                                  this.right = new Empty:120(
                                                                                                   this.color = [r=255,g=255,b=255]
                                                                                                   this.up = new Node:121(
                                                                                                    this.color = [r=255,g=175,b=175]
                                                                                                    this.up = <truncated; objects are too deeply nested to print>
                                                                                                    this.down = <truncated; objects are too deeply nested to print>
                                                                                                    this.left = <truncated; objects are too deeply nested to print>
                                                                                                    this.right = <truncated; objects are too deeply nested to print>
                                                                                                    this.changed = true)
                                                                                                   this.down = Node:119
                                                                                                   this.left = Node:116
                                                                                                   this.right = new Node:122(
                                                                                                    this.color = [r=255,g=0,b=255]
                                                                                                    this.up = <truncated; objects are too deeply nested to print>
                                                                                                    this.down = <truncated; objects are too deeply nested to print>
                                                                                                    this.left = <truncated; objects are too deeply nested to print>
                                                                                                    this.right = <truncated; objects are too deeply nested to print>
                                                                                                    this.changed = true)
                                                                                                   this.changed = false)
                                                                                                  this.changed = true)
                                                                                                 this.left = Node:104
                                                                                                 this.right = Node:121
                                                                                                 this.changed = false)
                                                                                                this.left = Empty:105
                                                                                                this.right = new Empty:123(
                                                                                                 this.color = [r=255,g=255,b=255]
                                                                                                 this.up = new Node:124(
                                                                                                  this.color = [r=255,g=175,b=175]
                                                                                                  this.up = new Empty:125(
                                                                                                   this.color = [r=255,g=255,b=255]
                                                                                                   this.up = new Node:126(
                                                                                                    this.color = [r=255,g=175,b=175]
                                                                                                    this.up = <truncated; objects are too deeply nested to print>
                                                                                                    this.down = <truncated; objects are too deeply nested to print>
                                                                                                    this.left = <truncated; objects are too deeply nested to print>
                                                                                                    this.right = <truncated; objects are too deeply nested to print>
                                                                                                    this.changed = true)
                                                                                                   this.down = Node:124
                                                                                                   this.left = Node:112
                                                                                                   this.right = new Node:127(
                                                                                                    this.color = [r=255,g=0,b=255]
                                                                                                    this.up = <truncated; objects are too deeply nested to print>
                                                                                                    this.down = <truncated; objects are too deeply nested to print>
                                                                                                    this.left = <truncated; objects are too deeply nested to print>
                                                                                                    this.right = <truncated; objects are too deeply nested to print>
                                                                                                    this.changed = true)
                                                                                                   this.changed = false)
                                                                                                  this.down = Empty:123
                                                                                                  this.left = Empty:113
                                                                                                  this.right = new Empty:128(
                                                                                                   this.color = [r=255,g=255,b=255]
                                                                                                   this.up = Node:127
                                                                                                   this.down = new Node:129(
                                                                                                    this.color = [r=255,g=0,b=255]
                                                                                                    this.up = <truncated; objects are too deeply nested to print>
                                                                                                    this.down = <truncated; objects are too deeply nested to print>
                                                                                                    this.left = <truncated; objects are too deeply nested to print>
                                                                                                    this.right = <truncated; objects are too deeply nested to print>
                                                                                                    this.changed = true)
                                                                                                   this.left = Node:124
                                                                                                   this.right = new Edge:130(
                                                                                                    this.color = [r=255,g=0,b=0]
                                                                                                    this.changed = true)
                                                                                                   this.changed = false)
                                                                                                  this.changed = true)
                                                                                                 this.down = Node:121
                                                                                                 this.left = Node:114
                                                                                                 this.right = Node:129
                                                                                                 this.changed = false)
                                                                                                this.changed = true)
                                                                                               this.left = Node:106
                                                                                               this.right = Node:124
                                                                                               this.changed = false)
                                                                                              this.left = Empty:107
                                                                                              this.right = Empty:125
                                                                                              this.changed = true)
                                                                                             this.left = Node:108
                                                                                             this.right = Node:126
                                                                                             this.changed = false)
                                                                                            this.changed = true)
                                                                                           this.down = Node:106
                                                                                           this.left = Node:87
                                                                                           this.right = Node:112
                                                                                           this.changed = false)
                                                                                          this.down = Empty:105
                                                                                          this.left = Empty:88
                                                                                          this.right = Empty:113
                                                                                          this.changed = true)
                                                                                         this.down = Node:104
                                                                                         this.left = Node:89
                                                                                         this.right = Node:114
                                                                                         this.changed = false)
                                                                                        this.down = Empty:103
                                                                                        this.left = Empty:90
                                                                                        this.right = Empty:115
                                                                                        this.changed = true)
                                                                                       this.down = Node:102
                                                                                       this.left = Node:91
                                                                                       this.right = Node:116
                                                                                       this.changed = false)
                                                                                      this.down = Empty:101
                                                                                      this.left = Empty:92
                                                                                      this.right = Empty:117
                                                                                      this.changed = true)
                                                                                     this.down = Node:100
                                                                                     this.left = Node:93
                                                                                     this.right = Node:118
                                                                                     this.changed = false)
                                                                                    this.down = Empty:99
                                                                                    this.left = Empty:94
                                                                                    this.right = new Empty:131(
                                                                                     this.color = [r=255,g=255,b=255]
                                                                                     this.up = Node:118
                                                                                     this.down = new Node:132(
                                                                                      this.color = [r=255,g=0,b=255]
                                                                                      this.up = Empty:131
                                                                                      this.down = new Empty:133(
                                                                                       this.color = [r=255,g=255,b=255]
                                                                                       this.up = Node:132
                                                                                       this.down = new Edge:134(
                                                                                        this.color = [r=255,g=0,b=0]
                                                                                        this.changed = true)
                                                                                       this.left = Node:98
                                                                                       this.right = new Node:135(
                                                                                        this.color = [r=255,g=175,b=175]
                                                                                        this.up = new Empty:136(
                                                                                         this.color = [r=255,g=255,b=255]
                                                                                         this.up = new Node:137(
                                                                                          this.color = [r=255,g=175,b=175]
                                                                                          this.up = new Empty:138(
                                                                                           this.color = [r=255,g=255,b=255]
                                                                                           this.up = Node:119
                                                                                           this.down = Node:137
                                                                                           this.left = Node:118
                                                                                           this.right = new Node:139(
                                                                                            this.color = [r=255,g=0,b=255]
                                                                                            this.up = new Empty:140(
                                                                                             this.color = [r=255,g=255,b=255]
                                                                                             this.up = Node:122
                                                                                             this.down = Node:139
                                                                                             this.left = Node:119
                                                                                             this.right = new Edge:141(
                                                                                             Items skipped)
                                                                                            Items skipped)
                                                                                           Items skipped)
                                                                                          Items skipped)
                                                                                         Items skipped)
                                                                                        Items skipped)
                                                                                       Items skipped)
                                                                                      Items skipped)
                                                                                     Items skipped)
                                                                                    Items skipped)
                                                                                   Items skipped)
                                                                                  Items skipped)
                                                                                 Items skipped)
                                                                                Items skipped)
                                                                               Items skipped)
                                                                              Items skipped)
                                                                             Items skipped)
                                                                            Items skipped)
                                                                           Items skipped)
                                                                          Items skipped)
                                                                         Items skipped)
                                                                        Items skipped)
                                                                       Items skipped)
                                                                      Items skipped)
                                                                     Items skipped)
                                                                    Items skipped)
                                                                   Items skipped)
                                                                  Items skipped)
                                                                 Items skipped)
                                                                Items skipped)
                                                               Items skipped)
                                                              Items skipped)
                                                             Items skipped)
                                                            Items skipped)
                                                           Items skipped)
                                                          Items skipped)
                                                         Items skipped)
                                                        Items skipped)
                                                       Items skipped)
                                                      Items skipped)
                                                     Items skipped)
                                                    Items skipped)
                                                   Items skipped)
                                                  Items skipped)
                                                 Items skipped)
                                                Items skipped)
                                               Items skipped)
                                              Items skipped)
                                             Items skipped)
                                            Items skipped)
                                           Items skipped)
                                          Items skipped)
                                         Items skipped)
                                        Items skipped)
                                       Items skipped)
                                      Items skipped)
                                     Items skipped)
                                    Items skipped)
                                   Items skipped)
                                  Items skipped)
                                 Items skipped)
                                Items skipped)
                               Items skipped)
                              Items skipped)
                             Items skipped)
                            Items skipped)
                           Items skipped)
                          Items skipped)
                         Items skipped)
                        Items skipped)
                       Items skipped)
                      Items skipped)
                     Items skipped)
                    Items skipped)
                   Items skipped)
                  Items skipped)
                 Items skipped)
                Items skipped)
               Items skipped)
              Items skipped)
             Items skipped)
            Items skipped)
           Items skipped)
          Items skipped)
         Items skipped)
        Items skipped)
       Items skipped)
      Items skipped)
     Items skipped)
    Items skipped)
   Items skipped
   }
  Items skipped
  }
 Items skipped)
Items skipped)
---------------
impworld version 1.0 --- 26 June  2012
-----------------------------------------


Ran 49 tests.
All tests passed.

--- END OF TEST RESULTS ---
WARNING: A terminally deprecated method in java.lang.System has been called
WARNING: System::setSecurityManager has been called by tester.Main (file:/C:/Fundies%202/EclipseJars/tester.jar)
WARNING: Please consider reporting this to the maintainers of tester.Main
WARNING: System::setSecurityManager will be removed in a future release
