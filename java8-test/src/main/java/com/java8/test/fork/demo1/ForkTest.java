package com.java8.test.fork.demo1;

import com.java8.test.fork.User;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ForkTest {

    public static void main(String[] args) {
        List<Integer> ids = Arrays.asList(1, 2, 3, 2, 2, 3, 4, 4, 2);
        BatchRequestService service = new BatchRequestService(ids, 0, ids.size());
        List<User> result = service.compute();
        System.out.println(result);
    }

    @AllArgsConstructor
    public static class BatchRequestService extends RecursiveTask<List<User>> {
        private int size = 200;
        private int length;

        private int start,
                end;

        private List<Integer> ids;

        public BatchRequestService(List<Integer> ids, int start, int end) {
            this.start = start;
            this.end = end;
            this.ids = ids;
            this.length = ids.size();
        }

        @Override
        protected List<User> compute() {

            if (ids.size() < size) {
                return requestRemoteAPI(ids);
            }
            BatchRequestService leftTask =
                    new BatchRequestService(ids, start, start + length / 2);
            leftTask.fork();
            BatchRequestService rightTask =
                    new BatchRequestService(ids, start + length / 2, end);
            List<User> rightResult = rightTask.compute();
            List<User> leftResult = leftTask.join();
            leftResult.addAll(rightResult);
            return leftResult;
        }

        private List<User> requestRemoteAPI(List<Integer> userIds) {
            return userIds.stream().map(x -> new User(x, x.toString() + "---")).collect(Collectors.toList());
        }
    }
}
