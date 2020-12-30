package com.jaymes.common;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {

  private int code;
  private String msg;
  private T data;

  /**
   * 成功时候的调用
   */
  public static <T> Result<T> success(T data) {
    return new Result<T>(data);
  }

  /**
   * 失败时候的调用
   */
  public static <T> Result<T> error(CodeMsg codeMsg) {
    return new Result<T>(codeMsg);
  }

  private Result(T data) {
    this.data = data;
  }

  private Result(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  private Result(CodeMsg codeMsg) {
    if (codeMsg != null) {
      this.code = codeMsg.getCode();
      this.msg = codeMsg.getMsg();
    }
  }


  public static int lengthOfLIS(int[] nums) {

    int[] ends = new int[nums.length];
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      int l = 0;
      int r = max;
      while (l < r) {
        int mid = (l + r) >> 1;
        if (nums[i] > ends[mid]) {
          l = mid + 1;
        } else {
          r = mid;
        }
      }
      max = Math.max(l+1, max);
      ends[l] = nums[i];
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a = {10,9,2,5,3,7,101,18};
    System.out.println(lengthOfLIS(a));
  }
}

