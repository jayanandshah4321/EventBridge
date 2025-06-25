import http from 'k6/http';
import { check } from 'k6';

export const options = {
  vus: 10, // 10 virtual users to simulate high concurrency
  iterations: 10, // each user tries once
};

export default function () {
  const url = 'http://localhost:8080/api/bookings/book?userId=1&eventId=2&numberOfPlayers=1';

  const res = http.post(url, null, {
    headers: {
      'Content-Type': 'application/json',
    },
  });

  check(res, {
    'is status 200': (r) => r.status === 200,
    'is status 400': (r) => r.status === 400,
    'is status 403': (r) => r.status === 403,
    'is status 409': (r) => r.status === 409,
  });
}
