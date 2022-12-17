(ns aoc.day14
  [:require [clojure.string :as str]])

(def input "520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n490,15 -> 501,15 -> 501,14\n503,68 -> 508,68\n513,60 -> 513,61 -> 523,61 -> 523,60\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n524,136 -> 529,136\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n509,64 -> 514,64\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n518,46 -> 522,46\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n521,71 -> 521,73 -> 514,73 -> 514,79 -> 527,79 -> 527,73 -> 525,73 -> 525,71\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n518,82 -> 518,83 -> 529,83 -> 529,82\n530,124 -> 535,124\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n542,112 -> 547,112\n536,118 -> 541,118\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n528,139 -> 528,141 -> 521,141 -> 521,146 -> 534,146 -> 534,141 -> 531,141 -> 531,139\n534,133 -> 539,133\n543,118 -> 548,118\n508,49 -> 508,52 -> 504,52 -> 504,55 -> 516,55 -> 516,52 -> 514,52 -> 514,49\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n508,49 -> 508,52 -> 504,52 -> 504,55 -> 516,55 -> 516,52 -> 514,52 -> 514,49\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n500,18 -> 500,20 -> 496,20 -> 496,24 -> 511,24 -> 511,20 -> 504,20 -> 504,18\n530,130 -> 535,130\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n508,49 -> 508,52 -> 504,52 -> 504,55 -> 516,55 -> 516,52 -> 514,52 -> 514,49\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n546,115 -> 551,115\n524,46 -> 528,46\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n521,71 -> 521,73 -> 514,73 -> 514,79 -> 527,79 -> 527,73 -> 525,73 -> 525,71\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n537,124 -> 542,124\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n528,139 -> 528,141 -> 521,141 -> 521,146 -> 534,146 -> 534,141 -> 531,141 -> 531,139\n500,18 -> 500,20 -> 496,20 -> 496,24 -> 511,24 -> 511,20 -> 504,20 -> 504,18\n539,115 -> 544,115\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n521,71 -> 521,73 -> 514,73 -> 514,79 -> 527,79 -> 527,73 -> 525,73 -> 525,71\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n533,127 -> 538,127\n518,82 -> 518,83 -> 529,83 -> 529,82\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n506,66 -> 511,66\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n535,161 -> 535,162 -> 544,162 -> 544,161\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n500,18 -> 500,20 -> 496,20 -> 496,24 -> 511,24 -> 511,20 -> 504,20 -> 504,18\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n490,15 -> 501,15 -> 501,14\n535,161 -> 535,162 -> 544,162 -> 544,161\n508,49 -> 508,52 -> 504,52 -> 504,55 -> 516,55 -> 516,52 -> 514,52 -> 514,49\n518,82 -> 518,83 -> 529,83 -> 529,82\n513,66 -> 518,66\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n558,124 -> 563,124\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n528,139 -> 528,141 -> 521,141 -> 521,146 -> 534,146 -> 534,141 -> 531,141 -> 531,139\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n554,121 -> 559,121\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n508,49 -> 508,52 -> 504,52 -> 504,55 -> 516,55 -> 516,52 -> 514,52 -> 514,49\n540,121 -> 545,121\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n547,121 -> 552,121\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n500,18 -> 500,20 -> 496,20 -> 496,24 -> 511,24 -> 511,20 -> 504,20 -> 504,18\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n512,46 -> 516,46\n544,124 -> 549,124\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n521,71 -> 521,73 -> 514,73 -> 514,79 -> 527,79 -> 527,73 -> 525,73 -> 525,71\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n528,139 -> 528,141 -> 521,141 -> 521,146 -> 534,146 -> 534,141 -> 531,141 -> 531,139\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n528,139 -> 528,141 -> 521,141 -> 521,146 -> 534,146 -> 534,141 -> 531,141 -> 531,139\n533,121 -> 538,121\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n521,43 -> 525,43\n500,18 -> 500,20 -> 496,20 -> 496,24 -> 511,24 -> 511,20 -> 504,20 -> 504,18\n527,133 -> 532,133\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n531,136 -> 536,136\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n515,43 -> 519,43\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n521,71 -> 521,73 -> 514,73 -> 514,79 -> 527,79 -> 527,73 -> 525,73 -> 525,71\n521,71 -> 521,73 -> 514,73 -> 514,79 -> 527,79 -> 527,73 -> 525,73 -> 525,71\n513,60 -> 513,61 -> 523,61 -> 523,60\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n537,130 -> 542,130\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n508,49 -> 508,52 -> 504,52 -> 504,55 -> 516,55 -> 516,52 -> 514,52 -> 514,49\n545,136 -> 550,136\n538,136 -> 543,136\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n528,139 -> 528,141 -> 521,141 -> 521,146 -> 534,146 -> 534,141 -> 531,141 -> 531,139\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n508,49 -> 508,52 -> 504,52 -> 504,55 -> 516,55 -> 516,52 -> 514,52 -> 514,49\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n503,37 -> 503,31 -> 503,37 -> 505,37 -> 505,29 -> 505,37 -> 507,37 -> 507,29 -> 507,37 -> 509,37 -> 509,29 -> 509,37 -> 511,37 -> 511,32 -> 511,37 -> 513,37 -> 513,29 -> 513,37 -> 515,37 -> 515,34 -> 515,37 -> 517,37 -> 517,36 -> 517,37 -> 519,37 -> 519,36 -> 519,37\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n510,68 -> 515,68\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n550,118 -> 555,118\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n528,139 -> 528,141 -> 521,141 -> 521,146 -> 534,146 -> 534,141 -> 531,141 -> 531,139\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n532,109 -> 532,103 -> 532,109 -> 534,109 -> 534,106 -> 534,109 -> 536,109 -> 536,102 -> 536,109 -> 538,109 -> 538,107 -> 538,109 -> 540,109 -> 540,107 -> 540,109 -> 542,109 -> 542,100 -> 542,109 -> 544,109 -> 544,106 -> 544,109\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n521,71 -> 521,73 -> 514,73 -> 514,79 -> 527,79 -> 527,73 -> 525,73 -> 525,71\n541,133 -> 546,133\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n513,60 -> 513,61 -> 523,61 -> 523,60\n518,40 -> 522,40\n517,68 -> 522,68\n500,18 -> 500,20 -> 496,20 -> 496,24 -> 511,24 -> 511,20 -> 504,20 -> 504,18\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n520,96 -> 520,92 -> 520,96 -> 522,96 -> 522,86 -> 522,96 -> 524,96 -> 524,92 -> 524,96 -> 526,96 -> 526,93 -> 526,96 -> 528,96 -> 528,95 -> 528,96 -> 530,96 -> 530,88 -> 530,96 -> 532,96 -> 532,88 -> 532,96 -> 534,96 -> 534,91 -> 534,96 -> 536,96 -> 536,92 -> 536,96 -> 538,96 -> 538,88 -> 538,96\n529,159 -> 529,153 -> 529,159 -> 531,159 -> 531,151 -> 531,159 -> 533,159 -> 533,156 -> 533,159 -> 535,159 -> 535,152 -> 535,159 -> 537,159 -> 537,154 -> 537,159 -> 539,159 -> 539,156 -> 539,159\n535,161 -> 535,162 -> 544,162 -> 544,161\n551,124 -> 556,124\n500,18 -> 500,20 -> 496,20 -> 496,24 -> 511,24 -> 511,20 -> 504,20 -> 504,18\n")

(defn parse-input [input]
  (let [rock-lines (->> (str/split-lines input)
                        (map (fn [line]
                               (->> (str/split line #" -> ")
                                    (map #(->> (str/split % #",")
                                               (map parse-long)
                                               (vec)))
                                    (partition 2 1)
                                    (map vec)
                                    (vec))))
                        (reduce concat)
                        (vec))]
    {:rock-units (->> rock-lines
                      (mapcat (fn [[[x1 y1] [x2 y2]]]
                                (let [[minx maxx] (sort [x1 x2])
                                      [miny maxy] (sort [y1 y2])]
                                  (cond
                                    (= x1 x2) (map vector (repeat x1) (range miny (inc maxy)))
                                    (= y1 y2) (map vector (range minx (inc maxx)) (repeat y1))))))
                      (set))
     :sand-units #{}}))

(defn sand-step [state coord]
  (let [[x y] coord
        coords (for [coord' [[x (inc y)]
                             [(dec x) (inc y)]
                             [(inc x) (inc y)]]
                     :when (and (not (contains? (:sand-units state) coord'))
                                (not (contains? (:rock-units state) coord'))
                                (or (not (contains? state :floor))
                                    (< y (:floor state))))]
                 coord')]
    (if (empty? coords)
      coord
      (first coords))))

(defn add-sand [state]
  (let [maxy (->> (:rock-units state)
                  (map second)
                  (reduce max))]
    (loop [[_ y :as coord] [500 -1]]
      (if (and (not (contains? state :floor))
               (= y maxy))
        :keeps-falling
        (let [coord' (sand-step state coord)]
          (if (= coord' coord)
            (if (= coord' [500 0])
              :source-blocked
              (update state :sand-units conj coord'))
            (recur coord')))))))

(defn simulate-until-end [state]
  (->> state
       (iterate add-sand)
       (take-while map?)
       last))

(defn part1 []
  (->> (parse-input input)
       (simulate-until-end)
       :sand-units
       count))

(defn part2 []
  (let [state (parse-input input)
        maxy (->> (:rock-units state)
                  (map second)
                  (reduce max))]
    (->> (assoc state :floor (+ 1 maxy))
         (simulate-until-end)
         :sand-units
         count
         inc)))

(defn visualize-top [state]
  (str/join "\n"
            (for [y (range 0 11)]
              (apply str
                     (for [x (range 470 530)]
                       (cond
                         (contains? (:rock-units state) [x y]) "#"
                         (contains? (:sand-units state) [x y]) "o"
                         :else "."))))))