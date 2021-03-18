import Product from '..';
import Root from '../../../../../Root';

const productMock = {
  id: 12,
  name: 'Cat Tee Black T-Shirt',
  description: '4 MSL',
  quantityType: 'Adet',
  price: 10.9,
  code: "0001"
};

it('mount without crashing', () => {
  const wrapped = mount(
    <Root>
      <Product product={productMock} addProduct={() => {}} />
    </Root>
  );
  wrapped.unmount();
});
