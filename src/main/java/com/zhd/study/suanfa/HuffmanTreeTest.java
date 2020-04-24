package com.zhd.study.suanfa;

/**
 * @author: zhanghaodong
 * @description {https://baijiahao.baidu.com/s?id=1663514710675419737&wfr=spider&for=pc}
 * @date: 2020-04-24 14:35
 */
public class HuffmanTreeTest {

    public static void main(String[] args) {
        int[] weights = {2, 3, 7, 9, 18, 25};

        HuffmanTree huffmanTree = new HuffmanTree();

        huffmanTree.createHuffman(weights);
        huffmanTree.output(huffmanTree);
    }


}
