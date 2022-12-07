(ns aoc.day7
  [:require [clojure.string :as str]])

(def input "$ cd /\n$ ls\ndir bzgf\n199775 dngdnvv.qdf\ndir fhhwv\ndir gzlpvdhd\ndir htczftcn\n23392 lbcgmm\n251030 lsw.jgr\n305227 nflgvsgz\ndir qcqg\ndir qtqpw\ndir qzcdscbp\ndir rfgvg\ndir rzb\n202033 zqzlbvgl\n$ cd bzgf\n$ ls\n80802 htczftcn.bdr\n$ cd ..\n$ cd fhhwv\n$ ls\ndir bjml\n274615 bzmwfgf.wwp\ndir ddpf\ndir jngvpc\n220692 lbcgmm\ndir pffdg\ndir prwpzhb\n21309 qtqpw.mhb\n12989 vbvt\n46352 vhnsp.dhg\n$ cd bjml\n$ ls\n307491 bzmwfgf.wwp\ndir cwghv\n164053 dngdnvv.qdf\n144223 jbc.zgp\n274358 mcqbcttc.mwr\ndir pffdg\n573 spbw.mnt\ndir vdd\ndir zzvs\n$ cd cwghv\n$ ls\n298079 htczftcn\n33689 nflgvsgz\n287144 svfqpfm.bgs\ndir tdgw\n164244 tzrz.hms\n52508 zmm.ndc\n$ cd tdgw\n$ ls\n195017 htczftcn.cnl\n$ cd ..\n$ cd ..\n$ cd pffdg\n$ ls\n64762 bbqh.wzf\ndir bgzdv\n56148 bhrw.jls\ndir hqwmfdj\n28260 htczftcn.lvs\ndir lzhz\ndir nbmsrl\n91675 qtqpw.hsj\n$ cd bgzdv\n$ ls\n205543 srzlfd.hnq\n$ cd ..\n$ cd hqwmfdj\n$ ls\n37910 dzpgcrn.rbj\n290553 mrrngcdr\n175411 nflgvsgz.tsj\n$ cd ..\n$ cd lzhz\n$ ls\n73620 bbtdnpvf\ndir jbc\n$ cd jbc\n$ ls\n125475 tzrz.hms\n$ cd ..\n$ cd ..\n$ cd nbmsrl\n$ ls\n106099 dngdnvv.qdf\n$ cd ..\n$ cd ..\n$ cd vdd\n$ ls\n136746 vlc.vcp\n$ cd ..\n$ cd zzvs\n$ ls\n8406 dngdnvv.qdf\ndir nflgvsgz\n55902 qtqpw.lcc\ndir vmbrt\n269256 whjmbnm.ngd\n$ cd nflgvsgz\n$ ls\ndir mdgzvzbs\ndir nlzc\n$ cd mdgzvzbs\n$ ls\n88913 ggvd\n$ cd ..\n$ cd nlzc\n$ ls\n37916 rww\n$ cd ..\n$ cd ..\n$ cd vmbrt\n$ ls\n194547 dwvbcvw.tmv\n54334 htczftcn.bqh\n299821 jnqgz\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ddpf\n$ ls\ndir plv\n$ cd plv\n$ ls\n178177 dzsns\n$ cd ..\n$ cd ..\n$ cd jngvpc\n$ ls\n276675 dcgmtcb\n108711 htczftcn.dsr\ndir jbc\n280837 rqprcth.dhp\n84724 tzrz.hms\n$ cd jbc\n$ ls\ndir htczftcn\n74153 tzrz.hms\n$ cd htczftcn\n$ ls\ndir nflgvsgz\n$ cd nflgvsgz\n$ ls\n208092 nnrd.zrj\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd pffdg\n$ ls\n264811 htczftcn\n$ cd ..\n$ cd prwpzhb\n$ ls\n181249 fstwrdqp\ndir jbc\n140710 nflgvsgz\ndir pffdg\n103710 qlqfc.qbm\ndir qtqpw\n15917 sqgmfl\ndir zhcs\n171467 zls.tmj\n$ cd jbc\n$ ls\n12337 tzrz.hms\n$ cd ..\n$ cd pffdg\n$ ls\n302369 cshb.fbd\n251192 htczftcn.vnn\ndir msdtpqqd\ndir sgtqmfgq\n$ cd msdtpqqd\n$ ls\n42614 mptlnbp\n$ cd ..\n$ cd sgtqmfgq\n$ ls\n81125 jbc.szn\n$ cd ..\n$ cd ..\n$ cd qtqpw\n$ ls\n160846 dngdnvv.qdf\n$ cd ..\n$ cd zhcs\n$ ls\n166883 vtz.bvb\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd gzlpvdhd\n$ ls\ndir ffvz\n$ cd ffvz\n$ ls\n74736 jvb.ppb\n$ cd ..\n$ cd ..\n$ cd htczftcn\n$ ls\ndir dqbml\n149343 gqjgvz\n112647 lbcgmm\ndir lbm\n192438 mpdzgzm\ndir nflgvsgz\ndir pffdg\ndir qtqpw\n149952 qtqpw.gdv\ndir rcnwghg\n134076 rvdsldpf.zqc\ndir sglgqlp\ndir wrfmf\n$ cd dqbml\n$ ls\ndir bphwp\n303757 gglsqjbz.ffb\ndir htczftcn\ndir hwm\n56134 pml.pmq\ndir rsv\n108298 tzrz.hms\n201663 wqqdrml.wsl\n$ cd bphwp\n$ ls\n94931 dngdnvv.qdf\n90517 jsgl\n118142 pzl\n7387 tzrz.hms\n$ cd ..\n$ cd htczftcn\n$ ls\n300063 ctfgqld\ndir htczftcn\ndir jhldlsc\ndir nflgvsgz\n125403 pffdg\n25617 pffdg.zdg\ndir qddqn\ndir tpmbzslr\n$ cd htczftcn\n$ ls\ndir zzpnff\n$ cd zzpnff\n$ ls\n79133 ssznrqlz.llw\n$ cd ..\n$ cd ..\n$ cd jhldlsc\n$ ls\ndir swcbqqd\n$ cd swcbqqd\n$ ls\n11202 htczftcn.cjs\n111869 htczftcn.nvb\n$ cd ..\n$ cd ..\n$ cd nflgvsgz\n$ ls\n37140 bffzwn.gqj\ndir glmvgr\n233206 lpclj\n79112 lpqh.ldp\n254611 qtqpw\ndir rmbwlbt\n206666 tgbdwgn\n$ cd glmvgr\n$ ls\n115043 nflgvsgz.jgl\n$ cd ..\n$ cd rmbwlbt\n$ ls\ndir pffdg\n$ cd pffdg\n$ ls\n92779 tzrz.hms\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd qddqn\n$ ls\ndir jbc\n$ cd jbc\n$ ls\n268367 cdcs\n$ cd ..\n$ cd ..\n$ cd tpmbzslr\n$ ls\ndir dbfcp\ndir lgsdbjz\n$ cd dbfcp\n$ ls\n23450 nflgvsgz.nsl\n$ cd ..\n$ cd lgsdbjz\n$ ls\n248980 cqzqzb.nfh\n248463 tzrz.hms\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd hwm\n$ ls\n174410 jbc.rvr\n310669 tzrz.hms\n$ cd ..\n$ cd rsv\n$ ls\ndir rsdnfh\n$ cd rsdnfh\n$ ls\n276060 dngdnvv.qdf\n137576 zsznp.ccq\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd lbm\n$ ls\n201564 pffdg.rpv\n$ cd ..\n$ cd nflgvsgz\n$ ls\ndir jbc\ndir svmmm\ndir zfjdnbs\n$ cd jbc\n$ ls\ndir jbc\ndir mzt\ndir qzth\ndir scwdmwj\n$ cd jbc\n$ ls\n71570 hhdt\ndir ldzjsrm\n27263 qtqpw.tcg\n$ cd ldzjsrm\n$ ls\n217073 mlmwjq\n172121 nflgvsgz\n39275 pzrrlgt\n$ cd ..\n$ cd ..\n$ cd mzt\n$ ls\n130917 tzrz.hms\n$ cd ..\n$ cd qzth\n$ ls\n280896 dngdnvv.qdf\n6736 jbc.mtm\ndir nfsbdfv\n293220 nvzjz\ndir pffdg\n88075 ppmr\n$ cd nfsbdfv\n$ ls\n58157 bzmwfgf.wwp\ndir gmhbnffd\n65369 gmq.zvb\ndir htczftcn\ndir qmc\n278485 tvs.csj\n$ cd gmhbnffd\n$ ls\n195493 lbcgmm\n264234 mvqbqprp\n268782 rtqts.zls\n$ cd ..\n$ cd htczftcn\n$ ls\ndir lnhjgl\n$ cd lnhjgl\n$ ls\n80056 ffsrvl\n$ cd ..\n$ cd ..\n$ cd qmc\n$ ls\n29571 hqftnv.vwn\n$ cd ..\n$ cd ..\n$ cd pffdg\n$ ls\ndir bfdmm\n244334 ppf\ndir rggmf\n$ cd bfdmm\n$ ls\n296551 nflgvsgz.bch\n77180 tzrz.hms\n$ cd ..\n$ cd rggmf\n$ ls\n187569 dngdnvv.qdf\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd scwdmwj\n$ ls\ndir fhwnjp\n191201 gztswt.cvn\ndir jsbdjv\ndir nflgvsgz\ndir pffdg\n55401 qtqpw.hfv\n$ cd fhwnjp\n$ ls\n200583 tqrqsmwt\n$ cd ..\n$ cd jsbdjv\n$ ls\n46882 fgjhsb.szs\n156238 qtqpw.ctw\n$ cd ..\n$ cd nflgvsgz\n$ ls\n216012 nrfchlbs.wlw\n75316 qtqpw\n218733 vzldr\n$ cd ..\n$ cd pffdg\n$ ls\n75020 htczftcn.trw\n6370 snnvrrwb.qmm\n281520 vwstfbzg.jgq\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd svmmm\n$ ls\ndir bgh\n22016 czchmpc\n294397 dngdnvv.qdf\n14175 gdhtprt\ndir nclpsp\ndir pffdg\ndir qtqpw\ndir rwhq\n138882 smqdrdjs\n174171 vqjztp.zlr\n$ cd bgh\n$ ls\n172183 fhsfhhjn\n209572 phpdrzm\n$ cd ..\n$ cd nclpsp\n$ ls\n41222 bzmwfgf.wwp\n134164 dqbqvg\ndir dqtgwjn\ndir htczftcn\n210868 htczftcn.cjp\ndir jbc\n185752 lfrdq.wrm\ndir pwvbnc\ndir qhrtrsvm\n235340 rljlr.lfs\n$ cd dqtgwjn\n$ ls\ndir lbft\n118196 tpl.qgw\n83985 wbjmdmjm\n$ cd lbft\n$ ls\n187172 vrd\n$ cd ..\n$ cd ..\n$ cd htczftcn\n$ ls\n210604 hzlf\n154916 nflgvsgz\n115688 pffdg.ptz\n$ cd ..\n$ cd jbc\n$ ls\n122033 bzmwfgf.wwp\n105912 dngdnvv.qdf\n16049 nflgvsgz\n43497 slmwmtfn\n69357 tcsc.fgf\n$ cd ..\n$ cd pwvbnc\n$ ls\n10200 pfvsnqp.fwr\n$ cd ..\n$ cd qhrtrsvm\n$ ls\n193675 djmhdr.vcc\n108939 qtqpw\n$ cd ..\n$ cd ..\n$ cd pffdg\n$ ls\ndir dbtmdvq\n$ cd dbtmdvq\n$ ls\n127568 vhnqsm.ghq\n$ cd ..\n$ cd ..\n$ cd qtqpw\n$ ls\ndir crpzfdn\n$ cd crpzfdn\n$ ls\n135177 lpzrl.qvj\ndir qtldnjtd\n$ cd qtldnjtd\n$ ls\n88481 rlbzbjt.rrz\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd rwhq\n$ ls\n87944 bzmwfgf.wwp\n70798 dngdnvv.qdf\ndir hgqbmgch\ndir lrvncpvz\ndir nflgvsgz\n$ cd hgqbmgch\n$ ls\n293841 bzmwfgf.wwp\n286937 lwpf\ndir smfbjhw\ndir smqh\n103844 vmpqmzf.dnf\n$ cd smfbjhw\n$ ls\n186167 crgrnf.dns\ndir htczftcn\n$ cd htczftcn\n$ ls\n11324 gssn.rlt\n46905 tzrz.hms\n$ cd ..\n$ cd ..\n$ cd smqh\n$ ls\ndir pffdg\ndir zbg\n$ cd pffdg\n$ ls\n3570 bzmwfgf.wwp\n3335 fvpd.bct\n$ cd ..\n$ cd zbg\n$ ls\n108877 bzmwfgf.wwp\n310302 drhsdtcv.rrw\n152250 wfvvsg\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd lrvncpvz\n$ ls\n206419 nflgvsgz.tvr\n$ cd ..\n$ cd nflgvsgz\n$ ls\n286461 tzrz.hms\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd zfjdnbs\n$ ls\ndir htczftcn\ndir nflgvsgz\ndir scvzpf\n$ cd htczftcn\n$ ls\ndir jznspqtr\ndir tpz\n$ cd jznspqtr\n$ ls\n86738 hqftnv.vwn\n$ cd ..\n$ cd tpz\n$ ls\n126506 nflgvsgz.qnh\n$ cd ..\n$ cd ..\n$ cd nflgvsgz\n$ ls\n248446 gwjgdb.vrr\n$ cd ..\n$ cd scvzpf\n$ ls\ndir fpbbjmjf\n173634 gbpql\n141796 jzncpqb.pgc\n69394 rpvnchs\n65500 tzrz.hms\n$ cd fpbbjmjf\n$ ls\n224020 hhrjc\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd pffdg\n$ ls\n101444 clwdmcz.thc\n58211 dngdnvv.qdf\ndir mnj\ndir ncwh\n153413 nflgvsgz\ndir qtqpw\n141829 rrfs.ffn\n$ cd mnj\n$ ls\n298654 qtqpw\n$ cd ..\n$ cd ncwh\n$ ls\n133042 bmthlszz.mtr\ndir nflgvsgz\ndir nrsqtdz\n201720 wqhnfz\n$ cd nflgvsgz\n$ ls\ndir bqqmlffw\n115991 hsljpqpf\ndir htczftcn\n$ cd bqqmlffw\n$ ls\n49001 wltpcszs.bvj\n$ cd ..\n$ cd htczftcn\n$ ls\n264620 sfdssh.dnj\n260432 vgvzjl\n$ cd ..\n$ cd ..\n$ cd nrsqtdz\n$ ls\n258978 htczftcn\ndir jbc\ndir nsqlmlqp\n28767 pffdg.blw\n172773 tzrz.hms\n$ cd jbc\n$ ls\ndir ztphfs\n$ cd ztphfs\n$ ls\ndir gpps\n$ cd gpps\n$ ls\n74744 bzmwfgf.wwp\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd nsqlmlqp\n$ ls\ndir zvwvdzlv\n$ cd zvwvdzlv\n$ ls\n218727 hsjm.qgm\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd qtqpw\n$ ls\n171370 sdmhwctq.gzr\n$ cd ..\n$ cd ..\n$ cd qtqpw\n$ ls\ndir csqvzqdc\n131580 jbc.ppb\n126635 jbc.sbm\n302398 pbjd\n74429 vnpj\ndir zhmp\n$ cd csqvzqdc\n$ ls\n150923 jbc.grw\ndir lthhrqnn\n240950 pffdg.qbt\ndir rgzqq\n$ cd lthhrqnn\n$ ls\n123194 czl\n17546 pffdg\n$ cd ..\n$ cd rgzqq\n$ ls\n156566 jbc.hpw\n$ cd ..\n$ cd ..\n$ cd zhmp\n$ ls\n291659 dngdnvv.qdf\n309738 nflgvsgz.wmc\n$ cd ..\n$ cd ..\n$ cd rcnwghg\n$ ls\n108285 hvhlggsn\n308801 sgstjfqq.hln\n$ cd ..\n$ cd sglgqlp\n$ ls\ndir gnvnlrz\ndir nchq\ndir nlrbnwp\ndir zpg\n$ cd gnvnlrz\n$ ls\n62071 lgqrm\ndir pvt\ndir srdqwtcs\n$ cd pvt\n$ ls\ndir fpfddfw\ndir jbc\ndir jzrb\n$ cd fpfddfw\n$ ls\n257231 rgccj.dwc\n$ cd ..\n$ cd jbc\n$ ls\n118252 dsvsmgm.hdm\n$ cd ..\n$ cd jzrb\n$ ls\n157243 gcrfg.svt\n86860 pblts.jtd\n$ cd ..\n$ cd ..\n$ cd srdqwtcs\n$ ls\n267482 bzzghfpq.gmh\ndir cbd\ndir gwqzndrt\n288249 hhdmlslr\n286213 lwmhd\ndir rfvmgch\n121131 vbm.rbp\n$ cd cbd\n$ ls\n238535 jpbzwvd.rrw\n246606 wgl.gfg\n$ cd ..\n$ cd gwqzndrt\n$ ls\ndir twtpbh\n262912 vcmv.qcs\n$ cd twtpbh\n$ ls\n245819 dngdnvv.qdf\n295823 ghrnmd\n308384 pffdg.ptr\n231669 sqhnt.grn\n$ cd ..\n$ cd ..\n$ cd rfvmgch\n$ ls\n306719 cwr.jsm\n6449 hqftnv.vwn\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd nchq\n$ ls\n137003 qtqpw\n267253 zrgvq\n146047 zww.nrr\n$ cd ..\n$ cd nlrbnwp\n$ ls\n91151 tzrz.hms\n$ cd ..\n$ cd zpg\n$ ls\n68248 bzmwfgf.wwp\n$ cd ..\n$ cd ..\n$ cd wrfmf\n$ ls\ndir cdlwfjhp\n16673 dsctg\n304187 jbc\ndir lchrdvm\ndir nflgvsgz\ndir qtqpw\ndir rgdc\n306410 tzrz.hms\ndir zqzzg\n$ cd cdlwfjhp\n$ ls\ndir clshzcrc\ndir lplgpg\n$ cd clshzcrc\n$ ls\n191491 tzrz.hms\n$ cd ..\n$ cd lplgpg\n$ ls\n169864 cmhrmh.pjp\n$ cd ..\n$ cd ..\n$ cd lchrdvm\n$ ls\n149068 chwbr.spd\n250029 hqftnv.vwn\n23399 jbc\n69746 nflgvsgz.nmp\ndir pldctfcf\ndir vngv\n251416 zhf.qtz\n$ cd pldctfcf\n$ ls\n297722 bcpwcn\n276738 cspls\ndir gghvnc\n80690 htczftcn\ndir pffdg\n97610 qfjs.gps\ndir tvc\n106070 tzrz.hms\n$ cd gghvnc\n$ ls\n93770 dngdnvv.qdf\n$ cd ..\n$ cd pffdg\n$ ls\n180916 bzmwfgf.wwp\n$ cd ..\n$ cd tvc\n$ ls\n11724 gtgzwsq\n$ cd ..\n$ cd ..\n$ cd vngv\n$ ls\n92840 qtqpw.gcl\n$ cd ..\n$ cd ..\n$ cd nflgvsgz\n$ ls\ndir htczftcn\ndir lcjqzrwm\n54961 lfn.rmz\ndir plvbmb\ndir vdfwfg\n$ cd htczftcn\n$ ls\ndir gflz\ndir mpp\n292374 rzm\n$ cd gflz\n$ ls\ndir qpdn\n$ cd qpdn\n$ ls\n258037 ddqb.jhd\n$ cd ..\n$ cd ..\n$ cd mpp\n$ ls\n248628 pffdg.spt\n$ cd ..\n$ cd ..\n$ cd lcjqzrwm\n$ ls\n249155 mbz.gsq\n291857 nngm.nft\n100136 zwvwlg.hrs\n$ cd ..\n$ cd plvbmb\n$ ls\ndir nnrnwp\n163552 pffdg\n$ cd nnrnwp\n$ ls\n289041 bzmwfgf.wwp\n29165 nvcwzfpr\n$ cd ..\n$ cd ..\n$ cd vdfwfg\n$ ls\n264391 hqftnv.vwn\n309071 htczftcn.mrd\ndir wtqrrvvr\n$ cd wtqrrvvr\n$ ls\n230331 swhpd\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd qtqpw\n$ ls\n292434 hbjlsrw\n$ cd ..\n$ cd rgdc\n$ ls\ndir hqldlpg\n$ cd hqldlpg\n$ ls\n188809 rvjwsmnz.hqt\n$ cd ..\n$ cd ..\n$ cd zqzzg\n$ ls\n250923 qtqpw.zrb\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd qcqg\n$ ls\n309875 tzrz.hms\n$ cd ..\n$ cd qtqpw\n$ ls\ndir rcnp\n$ cd rcnp\n$ ls\n131725 bzmwfgf.wwp\n46663 chwfd\n$ cd ..\n$ cd ..\n$ cd qzcdscbp\n$ ls\ndir mtqt\n$ cd mtqt\n$ ls\n27367 llbt.pth\ndir mcrddwgp\n$ cd mcrddwgp\n$ ls\n77221 bzmwfgf.wwp\n37941 lrwlhz\ndir tcmrv\n85568 tzrz.hms\n$ cd tcmrv\n$ ls\ndir hprvr\n$ cd hprvr\n$ ls\n59831 qbchdjw.gjg\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd rfgvg\n$ ls\ndir qlg\n$ cd qlg\n$ ls\n142825 dngdnvv.qdf\n$ cd ..\n$ cd ..\n$ cd rzb\n$ ls\n250575 dngdnvv.qdf\ndir jbc\ndir lbg\n187509 plblzt.zzz\ndir qwjlvqdp\n237529 rrrqcl.pvt\ndir rsjg\n$ cd jbc\n$ ls\n253304 lpdndfn\n$ cd ..\n$ cd lbg\n$ ls\ndir fml\n$ cd fml\n$ ls\n106624 shm.fgh\n$ cd ..\n$ cd ..\n$ cd qwjlvqdp\n$ ls\ndir htczftcn\n$ cd htczftcn\n$ ls\ndir gnnzlzwd\n272143 jbc.ldg\ndir sztdtpjt\n$ cd gnnzlzwd\n$ ls\n218355 tzrz.hms\n$ cd ..\n$ cd sztdtpjt\n$ ls\n53466 tzrz.hms\n$ cd ..\n$ cd ..\n$ cd ..\n$ cd rsjg\n$ ls\n101889 cct.sfg\n")

(defn parse-ls-out [lines]
  (loop [[line & lines] lines, out []]
    (let [[size filename] (str/split line #" ")
          out' (conj out [filename (parse-long size)])]
      (if (or (nil? lines) (str/starts-with? (first lines) "$ "))
        [out' lines]
        (recur lines out')))))

(defn parse-cmd [[line & lines]]
  (assert (str/starts-with? line "$ "))
  (let [[name arg] (str/split (subs line 2) #" ")
        [files lines'] (if (= name "ls")
                         (parse-ls-out lines)
                         [nil lines])]
    [lines' {:name (keyword name), :arg arg, :files files}]))

(defn parse-input [input]
  (loop [lines (str/split-lines input), cmds []]
    (if (nil? lines)
      cmds
      (let [[lines' cmd] (parse-cmd lines)]
        (recur lines' (conj cmds cmd))))))

(defn tree [cmds]
  (let [f (fn [state cmd]
            (case (:name cmd)
              :cd (update state :cwd (fn [cwd]
                                       (case (:arg cmd)
                                         ".." (pop cwd)
                                         (conj cwd (:arg cmd)))))
              :ls (update state :tree (fn [tree]
                                        (-> tree
                                            (update-in (:cwd state) #(if (nil? %) {} %))
                                            (update-in (:cwd state) into (:files cmd)))))))]
    (:tree (reduce f {:cwd [], :tree {}} cmds))))

(tree (parse-input input))

(defn tree-size [tree]
  (reduce
    (fn [acc [k v]]
      (cond
        (int? v) (+ acc v)
        (map? v) (+ acc (tree-size v))))
    0
    tree))

(defn dir-sizes [tree]
  (loop [subtrees (->> (filter (comp map? second) tree)
                       (map (fn [[k v]] [[k] v])))
         sizes {}]
    (if (empty? subtrees)
      sizes
      (recur (->> (map (fn [[k v]]
                         (map (fn [[k' v']]
                                [(conj k k') v'])
                              v))
                       subtrees)
                  (apply concat)
                  (filter (comp map? second)))
             (into sizes
                   (map (fn [[k v]]
                          [k (tree-size v)])
                        subtrees))))))

(defn part1 []
  (->> (dir-sizes (tree (parse-input input)))
       (map second)
       (filter #(<= % 100000))
       (reduce +)))

(defn part2 []
  (let [tree (tree (parse-input input))
        missing-space (- 30000000 (- 70000000 (tree-size tree)))]
    (->> (dir-sizes tree)
         (map second)
         (filter #(>= % missing-space))
         (sort)
         (first))))