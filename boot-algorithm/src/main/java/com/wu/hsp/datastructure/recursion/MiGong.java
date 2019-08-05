package com.wu.hsp.datastructure.recursion;

/**
 * 迷宫 回溯
 */
public class MiGong {

    static int[][] arr = new int[8][8];
    public static void main(String[] args) {
        //设置上下的边界为1
        for(int i=0;i<8;i++){
            arr[0][i] = 1;
            arr[7][i] = 1;
        }
        //设置左右边界为1
        for(int i=0;i<8;i++){
            arr[i][0] = 1;
            arr[i][7] = 1;
        }
        arr[3][1] = 1;
        arr[3][2] = 1;
        arr[4][3] = 1;
        arr[4][4] = 1;
        arr[5][5] = 1;
        arr[5][6] = 1;
        setWay(arr,1,1);
        for(int i = 0;i< 8;i++){
            for(int j = 0;j< 8;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    //使用递归说明
    //map 表示 地图
    //j,j表示从地图哪个位置开始出发
    //如果小球能到map[6][6]位置 则说明通路找到
    //约定:当map[i][j] 为0 表示该点没有走过 为1表示墙，不能走  2表示通路，可以走 3表示该点已经走过，但是走不同
    //走迷宫时，需要确定一个策略(方法) 下->右->上->左
    public static boolean setWay(int[][] map , int i , int j){
        if(map[6][6] == 2){
            return true;
        }else if(map[i][j] == 0){
            map[i][j] = 2;
            if(setWay(map,i+1,j)){
                return true;
            }else if(setWay(map,i,j+1)){
                return true;
            }else if(setWay(map,i-1,j)){

                return true;
            }else if(setWay(map,i,j-1)){
                return true;
            }else{
                map[i][j] = 3;
                return false;
            }
        }

        return false;
    }
}
